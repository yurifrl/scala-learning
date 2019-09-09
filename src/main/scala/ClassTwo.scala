package class_two

import scala.util.Try

case class User(userId: Long)
case class Order(user: User, number: String)
case class StockReservation(order: Order, id: Long)

class ClassTwo {

  def fetchUser(userId: Long): Try[User] =
    Try(User(userId))
  def createOrder(user: User): Try[Order] =
    Try(Order(user, "#123"))
  def stockReservation(order: Order): Try[StockReservation] =
    Try(StockReservation(order, 1))
  def exportErp(reservation: StockReservation): Try[Unit] =
    Try(println(s"Exporting to SAP: $reservation"))

  def run(id: Long) = fetchUser(id)
    .flatMap(user => createOrder(user))
    .flatMap(order => stockReservation(order))
    .flatMap(stockReservation => exportErp(stockReservation))
    .recover {
      case e: IllegalArgumentException =>
        "ono, invalid argument"
      case e: RuntimeException =>
        "something went wrong"
      case e: Exception =>
        "sorry, duno"
    }

  def ugglyRun(id: Long) = for {
    user <- fetchUser(id)
    order <- createOrder(user)
    stock <- stockReservation(order)
    erpId <- exportErp(stock)
  } yield (user, order, stock, erpId)
}
