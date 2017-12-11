package queue

import akka.actor.ActorRef
import com.rabbitmq.client.{AMQP, Channel, DefaultConsumer, Envelope}
import model.{AckEnvelope, Consumable}
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization._

import scala.reflect.Manifest

final class MessageConsumer[T <: Consumable](exchangeName: String, binding: String, channel: Channel, supervisor: ActorRef)(implicit mf: Manifest[T]) {

  val autoAck = false

  implicit val formats = DefaultFormats

  val queueName: String = RabbitMQConnection.declareExchange(exchangeName, binding)

  channel.basicConsume(queueName, autoAck, new DefaultConsumer(channel) {
    override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
      val deliveryTag = envelope.getDeliveryTag
      val consumed = read[T](new String(body))(formats, mf)
      supervisor ! AckEnvelope(consumed, deliveryTag)
    }
  })
}
