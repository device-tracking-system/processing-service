package actor

import akka.actor.{Actor, PoisonPill, Props}
import model.{AckEnvelope, AggregatedResult, ProcessTask, TrackCreated}
import queue.{MessageProducer, RabbitMQConnection}
import repository.TrackDAO
import service.{Point, SignificantPointsService}

import scala.concurrent.ExecutionContext.Implicits.global

class Worker(producer: MessageProducer) extends Actor {

  override def receive = {

    case AckEnvelope(TrackCreated(ownerId, longitude, latitude), dl) =>
      TrackDAO.createTrack(ownerId, longitude, latitude).onComplete(ackAndShutdown(dl))

    case AckEnvelope(ProcessTask(ownerId, period, maxPoints, timestamp), dl) =>
      TrackDAO.findTrackByOwnerId(ownerId).map { all =>
        val coordinates = all
          .filter(_.happenedIn(period))
          .map(track => Point(track.latitude, track.longitude, track.timestamp))
        val significant = SignificantPointsService.choose(coordinates, maxPoints)
        val lastTimestamp = significant.map(_.timestamp).max
        if (lastTimestamp > timestamp) {
          producer.publish(AggregatedResult(ownerId, significant.map(_.toCoordinate), lastTimestamp))
        }
      }.onComplete(ackAndShutdown(dl))

  }

  private def ackAndShutdown(dl: Long): Any => Unit = { _ =>
      self ! PoisonPill
      RabbitMQConnection.ack(dl)
  }
}

object Worker {
  def props(producer: MessageProducer) = Props(new Worker(producer))
}