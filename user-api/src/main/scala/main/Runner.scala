package main

import akka.http.scaladsl.server.HttpApp
import endpoint.UserEndpoint
import repository.UserRepository
import quill.DbContext

object Runner extends HttpApp with App {
  val endpoint = new UserEndpoint(new UserRepository(new DbContext))

  override protected def routes = endpoint.routes

  startServer("0.0.0.0", 8081)
}
