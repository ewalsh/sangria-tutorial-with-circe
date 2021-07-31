scalaVersion := "2.13.6"

organization := "ai.economicdatasciences"

libraryDependencies ++= Seq(
  "org.sangria-graphql" %% "sangria" % "2.1.3",
  "org.sangria-graphql" %% "sangria-circe" % "1.3.2",
  "org.sangria-graphql" %% "sangria-slowlog" % "2.0.2",

  "org.sangria-graphql" %% "sangria-akka-http-core" % "0.0.2",
  "org.sangria-graphql" %% "sangria-akka-http-circe" % "0.0.2",

  "io.circe" %% "circe-core" % "0.14.1",
  "io.circe" %% "circe-generic" % "0.14.1",
  "io.circe" %% "circe-parser" % "0.14.1",
  "io.circe" %% "circe-optics" % "0.14.1",

  "com.typesafe.slick" %% "slick" % "3.3.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  // "org.slf4j" % "slf4j-nop" % "2.0.0-alpha2",
  // "org.slf4j" % "slf4j-api" % "2.0.0-alpha2",
  // "org.slf4j" % "slf4j-log4j12" % "2.0.0-alpha2",
  "com.microsoft.sqlserver" % "mssql-jdbc" % "9.2.1.jre11",

  "com.typesafe.akka" %% "akka-actor" % "2.6.15",
  // "com.typesafe.akka" %% "akka-slf4j" % "2.6.15",
  // "com.typesafe.akka" %% "akka-actor-typed" % "2.6.15",
  // "com.typesafe.akka" %% "akka-protobuf-v3" % "2.6.15",
  // "com.typesafe.akka" %% "akka-serialization-jackson" % "2.6.15",
  "com.typesafe.akka" %% "akka-stream" % "2.6.15",
  "com.typesafe.akka" %% "akka-http" % "10.2.4",
  "de.heikoseeberger" %% "akka-http-circe" % "1.37.0",

  "org.scalatest" %% "scalatest" % "3.3.0-SNAP3" % Test
)
