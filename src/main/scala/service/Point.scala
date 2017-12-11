package service

import com.peertopark.java.geocalc.{DegreeCoordinate, EarthCalc, Point => GeoPoint}
import model.Coordinate


case class Point(lat: Double, long: Double, timestamp: Long) {
  def toCoordinate = Coordinate(lat, long)
}

object PointOrdering extends Ordering[Point] {

  private val zeroPoint = new GeoPoint(new DegreeCoordinate(0), new DegreeCoordinate(0))

  private def distance(point: Point): Double = {
    val firstPoint = new GeoPoint(new DegreeCoordinate(point.lat), new DegreeCoordinate(point.long))
    EarthCalc.getDistance(firstPoint, zeroPoint)
  }

  def compare(a: Point, b: Point): Int = {
    distance(a).compareTo(distance(b))
  }

}
