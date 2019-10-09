package repository

import java.time.{ZoneId, ZoneOffset, ZonedDateTime}
import java.util.{Date, UUID}
import java.util.concurrent.Executors

import domain.User
import quill.DbContext

import scala.concurrent.{ExecutionContext, Future}

trait UserRepository {
  def fetchUser(id: UUID): Future[Option[User]]
  def insertUser(user: User): Future[Unit]
}

class UserRepositoryDefault(ctx: DbContext) extends UserRepository {
  import ctx._

  implicit val encodeZonedDateTime = MappedEncoding[ZonedDateTime, Date](z => Date.from(z.toInstant))
  implicit val decodeZonedDateTime = MappedEncoding[Date, ZonedDateTime]( d => d.toInstant.atZone(ZoneId.of("America/Sao_Paulo")))

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(11))

  private val users = quote {
    querySchema[User]("users",
      _.id -> "id",
      _.name -> "name",
      _.age -> "age",
      _.occupation -> "occupation",
      _.createdAt -> "created_at"
    )
  }

  //SELECT x1.id, x1.name, x1.age, x1.occupation FROM user x1 WHERE x1.id = ?
  override def fetchUser(id: UUID): Future[Option[User]] = {
    val q = quote {
      users.filter(_.id == lift(id))
    }

    Future(run(q).headOption)
  }

  //INSERT INTO user (id,name,age,occupation) VALUES (?, ?, ?, ?)
  override def insertUser(user: User): Future[Unit] = {
    val q = quote {
      users.insert(lift(user))
    }

    Future(run(q)).flatMap {
      case 1 => Future.unit
      case 0 => Future.failed(new RuntimeException("could not insert the value"))
    }
  }
}