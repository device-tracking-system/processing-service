import actor.Supervisor
import akka.actor.ActorSystem
import model.{ProcessTask, TrackCreated}
import queue.RabbitMQConnection.channel
import queue.{MessageConsumer, MessageProducer}
import settings.Conf._

object Main extends App {

  val system = ActorSystem("processingSystem")

  val producer = new MessageProducer(webExchange, webProducerBinding, channel)

  val processingSupervisor = system.actorOf(Supervisor.props(producer))

  val mobileConsumer = new MessageConsumer[TrackCreated](mobileExchange, mobileBinding, channel, processingSupervisor)

  val webConsumer = new MessageConsumer[ProcessTask](webExchange, webBinding, channel, processingSupervisor)

}