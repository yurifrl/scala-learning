package class_three

import scala.concurrent.Future
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global

case class User(userId: Long)
case class Order(user: User, number: String)
case class StockReservation(order: Order, id: Long)

class ClassThree {
  def fetchUser(userId: Long): Future[User] =
    Future { User(userId) }

  def createOrder(user: User): Future[Order] =
    Future { Order(user, "#123") }

  def stockReservation(order: Order): Future[StockReservation] =
    Future { StockReservation(order, 1) }

  def exportErp(reservation: StockReservation): Future[String] = {
    Future(s"SAP_ID=${reservation.order.number}_SAP")
  }

  def checkPrice(order: Order): Future[Unit] = Future.unit

  def checkFreight(order: Order): Future[Unit] = Future.unit

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


  def newRun(id: Long) = fetchUser(id)
    .flatMap(u => createOrder(u))
    .flatMap(o => stockReservation(o)
        .zip(checkPrice(o))
        .zip(checkFreight(o))
    )
    .flatMap {
      case((s, p), f) =>
        exportErp(s)
    }

  def ugglyRun(id: Long) = for {
    user <- fetchUser(id)
    order <- createOrder(user)
    stock <- stockReservation(order)
    erpId <- exportErp(stock)
  } yield (user, order, stock, erpId)

  def newUgglyRun(id: Long) = for {
    user <- fetchUser(id)
    order <- createOrder(user)
    (stock, price) <- stockReservation(order).zip(checkPrice(order))
    erpId <- exportErp(stock)
  } yield (user, order, stock, erpId, price)
}
