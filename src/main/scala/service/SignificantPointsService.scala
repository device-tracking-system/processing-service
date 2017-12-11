package service


object SignificantPointsService {

  implicit val pointsOrdering = PointOrdering

  private def everyNth(l: List[Point], n: Int) = {
    if (n == 0) l
    else l.zipWithIndex.collect { case (e, i) if ((i + 1) % n) == 0 => e }
  }

  def choose(points: List[Point], max: Int): List[Point] = {
    if (max == 0) List.empty
    else everyNth(points.sorted, points.size/max).takeRight(max)
  }

}
