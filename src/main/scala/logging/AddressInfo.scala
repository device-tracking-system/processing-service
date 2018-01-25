package logging

import scala.util.{Failure, Success, Try}

case class AddressInfo(protocol: String, host: String, port: Int) {

  val protocolColonHost: String =
    protocol + ":" + host

}

object AddressInfo {

  private val supportedProtocols = Set("tcp", "udp")

  def from(addressString: String): AddressInfo = {
    val trimmedAddress = addressString.trim

    val splitByProtocol = trimmedAddress.split("://")
    val protocol = checkProtocol(splitByProtocol.head)

    val hostAndPort = checkHostAndPort(splitByProtocol.tail)
    val host = hostFrom(hostAndPort)
    val port = portFrom(hostAndPort)

    AddressInfo(protocol, host, port)
  }

  private def checkProtocol(protocol: String): String = {
    if (supportedProtocols.contains(protocol)) {
      protocol
    } else {
      throw AddressInfoExtractionException(
        s"Protocol $protocol not supported. Supported: ${supportedProtocols.mkString(", ")}")
    }
  }

  private def checkHostAndPort(hostAndPort: Array[String]): Array[String] = {
    Try(hostAndPort.head.split(":")) match {
      case Success(split) =>
        split
      case Failure(throwable) =>
        throw AddressInfoExtractionException("Could not split host and port by ':'", throwable)
    }
  }

  private def hostFrom(hostAndPort: Array[String]): String = {
    Try(hostAndPort.head) match {
      case Success(host) =>
        if (host.nonEmpty) host else throw AddressInfoExtractionException("Host can not be empty")
      case Failure(throwable) =>
        throw AddressInfoExtractionException("Could not extract host", throwable)
    }
  }

  private def portFrom(hostAndPort: Array[String]): Int = {
    Try(hostAndPort(1).toInt) match {
      case Success(port) =>
        port
      case Failure(throwable) =>
        throw AddressInfoExtractionException("Could not extract port", throwable)
    }
  }

}

case class AddressInfoExtractionException(message: String) extends Exception(message) {
  def this(message: String, cause: Throwable) = {
    this(message)
    initCause(cause)
  }
}

object AddressInfoExtractionException {
  def apply(message: String, cause: Throwable): AddressInfoExtractionException =
    new AddressInfoExtractionException(message)
}

