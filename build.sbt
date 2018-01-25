name := "processing-service"

version := "0.1"

scalaVersion := "2.12.4"

val akkaVersion = "2.5.8"
val akkaHttpVersion = "10.0.11"

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

val processingService = (project in file(".")).enablePlugins(JavaServerAppPackaging)

val akka = Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
  "de.heikoseeberger" %% "akka-http-json4s" % "1.11.0" exclude("org.json4s", "json4s-core_2.11"),
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
)

val logging = Seq(
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.codehaus.janino" % "janino" % "2.7.8",
  "biz.paluch.logging" % "logstash-gelf" % "1.11.0",
  "org.codehaus.groovy" % "groovy-all" % "2.4.7" % "runtime"
)

val other = Seq(
"com.rabbitmq" % "amqp-client" % "5.0.0",
  "org.reactivemongo" %% "reactivemongo" % "0.12.6",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.json4s" %% "json4s-jackson" % "3.5.0",
  "com.peertopark.java" % "geocalc" % "1.1.0"
)

libraryDependencies ++= akka ++ logging ++ other

resourceDirectory in Compile := baseDirectory.value / "conf"
resourceDirectory in Test := baseDirectory.value / "conf"

unmanagedResourceDirectories in Compile += baseDirectory.value / "src/main/resources"
unmanagedResourceDirectories in Test += baseDirectory.value / "src/main/resources"

parallelExecution in Test := false