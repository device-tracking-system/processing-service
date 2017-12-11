package model

import java.time.Instant

case class Track(ownerId: String, latitude: Double, longitude: Double, timestamp: Long) {
  def happenedIn(minutes: Int): Boolean = (Instant.now().getEpochSecond - timestamp) < (minutes * 60)
}
