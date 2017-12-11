package queue

import java.util

import com.rabbitmq.client.{Channel, Connection, ConnectionFactory}
import settings.Conf

object RabbitMQConnection {

  val factory = new ConnectionFactory()
  factory.setUsername(Conf.queueUser)
  factory.setPassword(Conf.queuePassword)
  factory.setHost(Conf.queueHost)
  factory.setPort(Conf.queuePort)

  private val connection: Connection = factory.newConnection

  val channel: Channel = connection.createChannel

  def declareExchange(exchangeName: String, routingKey: String): String = {
    val queueName = exchangeName+"."+routingKey
    channel.exchangeDeclare(exchangeName, "direct", true)
    channel.queueDeclare(queueName, true, false, false, new util.HashMap())
    channel.queueBind(queueName, exchangeName, routingKey)
    queueName
  }

  def ack(dl: Long) = channel.basicAck(dl, false)

}
