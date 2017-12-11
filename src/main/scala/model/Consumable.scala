package model

sealed trait Consumable

//Last timestamp
case class ProcessTask(ownerId: String, period: Int, points: Int, timestamp: Long) extends Consumable

case class TrackCreated(ownerId: String, longitude: Double, latitude: Double) extends Consumable

case class AckEnvelope(consumable: Consumable, dl: Long)

