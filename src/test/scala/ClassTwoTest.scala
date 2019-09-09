package class_two

import org.scalatest.FunSuite
import scala.util.{Try,Success,Failure}

class ClassTwoTest extends FunSuite {
  test("Map") {
    val foo = new ClassTwo
    foo.run(1)
    // foo.ugglyRun(1).map((user, order, stock, _) => assert(user == 2))
  }
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

    list match {
      case head :: Nil =>
        head
      case first :: second :: tails =>
        first + second
      case head :: tail =>
        head
      case Nil =>
        0
    }
    val first :: second :: thrid :: tail = list
    assert(first == 1)
    assert(tail == Nil)

    val myNiceMap: Map[String, String] = Map("key" -> "value")

    val otherMap = myNiceMap.map {
      case (key, value) =>
        (value.toLowerCase(): String, key.toUpperCase(): String)
    }
    assert(otherMap == Map("value" -> "KEY"))

    // Talk about Try catch?
  }
}
