package domain

import java.time.ZonedDateTime
import java.util.UUID

import play.api.libs.json.Json

case class User(id: UUID,
                name: String,
                age: Int,
                createdAt: ZonedDateTime,
                occupation: Option[String])

object User {
  implicit val format = Json.format[User]
}
