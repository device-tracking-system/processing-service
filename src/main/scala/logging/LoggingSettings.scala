package logging

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging
import scala.collection.JavaConverters._

object LoggingSettings {
  Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionLoggingHandler)
  private val root = "root"
  private val gelfPrefix = "logging.gelf"
  private val stdoutPrefix = "logging.stdout"
  private val logLevelPath = "logging.loglevel"
  val config = ConfigFactory.load()
  val address = AddressInfo.from(config.getString(s"$gelfPrefix.address"))
  val timestamp = config.getString(s"$gelfPrefix.timestamp")
  val applicationName = config.getString(s"$gelfPrefix.application-name")
  val includeHostname = config.getBoolean(s"$gelfPrefix.includeHostname")
  val extractStackTrace = config.getBoolean(s"$gelfPrefix.extractStackTrace")
  val filterStackTrace = config.getBoolean(s"$gelfPrefix.filterStackTrace")
  val mdcProfiling = config.getBoolean(s"$gelfPrefix.mdcProfiling")
  val maximumMessageSize = config.getInt(s"$gelfPrefix.maximumMessageSize")
  val gelfFilterLevel = config.getString(s"$gelfPrefix.filter.level")
  val pattern = config.getString(s"$stdoutPrefix.pattern")
  val stdoutFilterLevel = config.getString(s"$stdoutPrefix.filter.level")
  val rootLogLevel = customLogLevel(root)
  val customPackages: Set[String] = config
    .getConfig(logLevelPath)
    .entrySet()
    .asScala
    .map(_.getKey)
    .toSet - root
  def customLogLevel(packageName: String): String =
    config.getString(s"$logLevelPath.$packageName")
}

object UncaughtExceptionLoggingHandler extends Thread.UncaughtExceptionHandler with StrictLogging {

  override def uncaughtException(thread: Thread, throwable: Throwable): Unit = {
    logger.error("Uncaught exception", throwable)
  }

}
