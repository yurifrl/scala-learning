package class_one

import org.scalatest.FunSuite

class ClassOneTest extends FunSuite {
  test("Deployer.handleRequest") {
    val foo = new ClassOne
    assert(foo.sum(1: Int, 2: Int) == 3)
    assert(MyNiceSingleton.x == 2)
    val order = Order(1, 2, 2000, "creditcard", "created")
    assert(order.status == "created")
    val paidOrder = order.copy(status = "paid", voucherCode = Some("WOW"))
    assert(paidOrder.status == "paid")
    paidOrder.printStatus
    paidOrder.voucherCode match {
      case None =>
      case Some(value) => assert(value == "WOW")
    }
    assert(foo.checkNumber(1) == "1 is odd!")
  }
}
