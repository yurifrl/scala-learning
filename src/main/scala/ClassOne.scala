package class_one

trait MyInterface {
  def sum(x: Int, y: Int): Int
}

class ClassOne extends MyInterface {
  def sum(x: Int, y: Int): Int = x + y

  def checkNumber(x: Int): String = {
    val result = if (x % 2 == 0) {
      s"$x is even!"
    } else if (x % 2 != 0) {
      s"$x is odd!"
    } else {
      "Ono!"
    }
    result
  }
}

case class Order(id: Long,
                 number: Long,
                 amount: Long,
                 paymentMethod: String = "credit",
                 status: String,
                 voucherCode: Option[String] = None) {

  def printStatus: Unit = println(status)
  override def toString: String = {
    "you shall nit pass!"
  }
}

// Singleton
object MyNiceSingleton {
  val x = 2
}

//
abstract class MyAbstractClass {
  val myValue: Int

  def sum(x: Int, y: Int)
}
