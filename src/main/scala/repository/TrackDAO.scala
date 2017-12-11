package repository

import java.time.Instant

import model.Track
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.{Macros, document}
import repository.MongoDBConnection.db

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TrackDAO {

  val trackCollection: Future[BSONCollection] = db.map(_.collection[BSONCollection]("tracks"))

  implicit def trackHandler = Macros.handler[Track]

  def createTrack(ownerId: String, longitude: Double, latitude: Double): Future[WriteResult] =
    trackCollection.flatMap(a => a.insert(Track(ownerId, longitude, latitude, Instant.now().getEpochSecond)))

  def findTrackByOwnerId(ownerId: String): Future[List[Track]] =
    trackCollection.flatMap(_.find(document("ownerId" -> ownerId)).cursor().collect[List]())

}
