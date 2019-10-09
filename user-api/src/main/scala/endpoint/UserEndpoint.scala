package endpoint

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.Location
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport._
import domain.User
import repository.UserRepository

class UserEndpoint(repository: UserRepository) {
  val routes: Route =
    pathPrefix("api" / "v1" / "users") {
      get {
        path(JavaUUID) { id =>
          val result = repository.fetchUser(id)

          // onComplete(result) {
          //   case Failure(e) => complete(StatusCodes.InternalServerError)
          //   case Success(None) => complete(StatusCodes.NotFound)
          //   case Sucess(Some(user: User)) => complete(user)
          // }

          onSuccess(result) {
            case None => complete(StatusCodes.NotFound)
            case Some(user: User) => complete(user)
          }
        }
      } ~ post {
        entity(as[User]) { user =>
          val result = repository.insertUser(user)
          onSuccess(result) {
            respondWithHeader(Location(s"/api/v1/users${user.id}")) {
              complete(StatusCodes.Created)
            }
          }
        }
      }
    }
}
