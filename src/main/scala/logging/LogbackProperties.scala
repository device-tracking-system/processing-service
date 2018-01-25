package logging

import java.util

import ch.qos.logback.classic.Level

import scala.collection.JavaConverters._

object LogbackProperties {

  val emptyValue = "-"

  val protocolColonHost = LoggingSettings.address.protocolColonHost
  val port = LoggingSettings.address.port

  val timestamp = LoggingSettings.timestamp
  val stdoutPattern = LoggingSettings.pattern

  val extractStackTrace = LoggingSettings.extractStackTrace
  val filterStackTrace = LoggingSettings.filterStackTrace
  val mdcProfiling = LoggingSettings.mdcProfiling
  val maximumMessageSize = LoggingSettings.maximumMessageSize

  val gelf = "gelf"
  val stdout = "stdout"

  val applicationNameKey = "appName"
  val applicationNameValue = LoggingSettings.applicationName

  val gelfFilterLevel: Level = logLevelFrom(LoggingSettings.gelfFilterLevel)
  val stdoutFilterLevel: Level = logLevelFrom(LoggingSettings.stdoutFilterLevel)

  val rootLogLevel: Level = logLevelFrom(LoggingSettings.rootLogLevel)
  val customLoggers: util.Map[String, Level] = LoggingSettings.customPackages
    .foldLeft(Map.empty[String, Level]) { (map, packageName) =>
      val level = customLogLevel(packageName)
      map + (packageName -> level)
    }
    .asJava

  def customLogLevel(packageName: String): Level =
    logLevelFrom(LoggingSettings.customLogLevel(packageName))

  private def logLevelFrom(level: String): Level =
    Level.valueOf(level)

}

