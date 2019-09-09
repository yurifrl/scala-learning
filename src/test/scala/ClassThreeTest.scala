package class_three

import org.scalatest.FunSuite
import scala.util.{Try,Success,Failure}
import scala.concurrent.ExecutionContext.Implicits.global

class ClassThreeTest extends FunSuite {
  test("Either") {
    val foo = new ClassThree

    foo.newRun(1)
      .map(x => assert(x == "SAP_ID=#123_SAP"))

    assert(1 == 1)
  }
}
