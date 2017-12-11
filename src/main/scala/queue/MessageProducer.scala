package queue

import com.rabbitmq.client.Channel
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization._


class MessageProducer(exchangeName: String, routingName: String, channel: Channel) {

  implicit val formats = DefaultFormats

  val basicProperties = null

  RabbitMQConnection.declareExchange(exchangeName, routingName)

  def publish[T <: AnyRef](message: T) = {
    channel.basicPublish(exchangeName, routingName, basicProperties, write[T](message).getBytes())
  }

}
