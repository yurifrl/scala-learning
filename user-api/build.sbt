name := "user-api"

version := "0.1"

scalaVersion := "2.12.9"

val akka = "2.5.25"
val akkaHttp =  "10.1.9"
val akkaHttpPlayJson = "1.27.0"
val postgreSqlDriver = "42.2.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttp,
  "com.typesafe.akka" %% "akka-stream" % akka,
  "com.typesafe.akka" %% "akka-slf4j" % akka,

  "de.heikoseeberger" %% "akka-http-play-json" % akkaHttpPlayJson,

  "io.getquill" %% "quill-jdbc" % "3.4.4",
  "org.postgresql" % "postgresql" % postgreSqlDriver,

  "org.liquibase" % "liquibase-core" % "3.6.3",

  "org.scalactic" %% "scalactic" % "3.0.8" % Test,
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,

  "com.typesafe.akka" %% "akka-stream-testkit" % akka % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttp % Test
)
