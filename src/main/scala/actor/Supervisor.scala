package actor

import akka.actor.{Actor, Props}
import queue.MessageProducer

class Supervisor(producer: MessageProducer) extends Actor {
  override def receive = {
    case any => context.actorOf(Worker.props(producer)) forward any
  }
}

object Supervisor {
  def props(producer: MessageProducer) = Props(new Supervisor(producer))
}
