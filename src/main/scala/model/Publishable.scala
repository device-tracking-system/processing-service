package model


sealed trait Publishable

case class Coordinate(lat: Double, long: Double)

//Newest event in batch timestamp
case class AggregatedResult(ownerId: String, data: List[Coordinate], timestamp: Long) extends Publishable
