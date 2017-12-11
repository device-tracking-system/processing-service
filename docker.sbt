
import com.typesafe.sbt.packager.docker.Cmd

javaOptions in Universal += "-Dconfig.resource=production.conf"

dockerUpdateLatest := true
//dockerRepository := Some("nameOfDockerRegistry")

dockerCommands := Seq(
  Cmd("FROM", "openjdk:8-jre-slim"),
  Cmd("LABEL", s"name=${name.value}", "author=mowczare", "organization=device-tracking-system"),

  Cmd("COPY", s"opt/docker /opt/${name.value}"),
  Cmd("WORKDIR", s"/opt/${name.value}"),
  Cmd("ENTRYPOINT", s"/opt/${name.value}/bin/${name.value}")
)
