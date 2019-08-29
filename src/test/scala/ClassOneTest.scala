package class_two

import org.scalatest.FunSuite
import scala.util.{Try,Success,Failure}

class ClassTwoTest extends FunSuite {
  test("Lists") {
    val list = List(1, 2, 3)
    val newList = list :+ 4
    assert(newList == List(1, 2, 3, 4))

    val otherList = list ++ List(5)
    assert(otherList == List(1, 2, 3, 5))

    val x = list.map(y => y * 2)
    assert(x == List(2, 4, 6))

    def fetchUsername(id: Long) : Try[String] = {
      Success("username")
    }
    fetchUsername(id = 1).map(println)
    fetchUsername(id = 1).map(x => assert(x == "username"))

    assert(list.headOption == Option(1))
    assert(List().headOption == None)
  }
}
