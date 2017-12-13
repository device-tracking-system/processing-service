package model


sealed trait Publishable

case class Coordinate(latitude: Double, longitude: Double)

//Newest event in batch timestamp
case class AggregatedResult(ownerId: String, data: List[Coordinate], timestamp: Long) extends Publishable
