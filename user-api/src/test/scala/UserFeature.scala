import java.time.ZonedDateTime
import java.util.UUID

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import endpoint.UserEndpoint
import org.scalatest.{FeatureSpec, Matchers}
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport._
import play.api.libs.json.{JsValue, Json}
import repository.MockUserRepository
import quill.DbContext

class UserFeature
    extends FeatureSpec
    with Matchers
    with ScalatestRouteTest
    with BeforeAndAfter {
  

  val repository = new UserRepositoryDefault(DbContext)
  val routes = new UserEndpoint(MockUserRepository).routes

  feature("user endpoint") {
    scenario("create user successfully") {
      val request =
        """
        |{
        |  "id": "b85947eb-6805-48aa-b501-9d849afd7d11",
        |  "name": "yuro",
        |  "age": 80,
        |  "createdAt": 1568311186141,
        |  "occupation": "dev"
        |}
        """.stripMargin
      Post("/api/v1/users/", request) ~> routes ~> check {
        status shouldBe StatusCodes.Created
      }
    }
    scenario("") {
      val id = "b85947eb-6805-48aa-b501-9d849afd7d11"

      Get(s"/api/1/users/$id") ~> routes ~> check {
        status shouldBe StatusCodes.OK

        val responseBody = responseAs[JsValue]

        (responseBody \ "id").as[UUID] shouldBe id
        (responseBody \ "name").as[String] shouldBe "yuro"
        (responseBody \ "age").as[Int] shouldBe 80
        (responseBody \ "occupation").as[String] shouldBe "dev"
        (responseBody \ "createdAt").asOpt[ZonedDateTime] shouldBe defined
      }
    }
  }
}
