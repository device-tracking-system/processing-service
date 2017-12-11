package service

import java.time.Instant

import org.scalatest.{Matchers, WordSpecLike}

class SignificantPointsTest extends WordSpecLike with Matchers {

  val t: Long = Instant.now().getEpochSecond
  val fullList: List[Point] = (1 to 10).map(n => Point(n, n, t)).toList
  val singleList: List[Point] = List(Point(5.0, 5.0, t))
  val emptyList: List[Point] = List.empty

  "SignificantPointsService" should {
    "choose correct number of points from a full list" in {
      val significant = SignificantPointsService.choose(fullList, 3)
      significant.length shouldBe 3

      val significant2 = SignificantPointsService.choose(fullList, 7)
      significant2.length shouldBe 7
    }

    "choose zero points from an empty list when 0 to choose" in {
      val significant = SignificantPointsService.choose(emptyList, 0)
      significant.length shouldBe 0
    }

    "choose zero points from an empty list when more to choose" in {
      val significantMore = SignificantPointsService.choose(emptyList, 5)
      significantMore.length shouldBe 0
    }

    "choose one point from a single-element-list when more to choose" in {
      val significantMore = SignificantPointsService.choose(singleList, 5)
      significantMore.length shouldBe 1
    }

    "choose one point from a single-element-list when one to choose" in {
      val significant = SignificantPointsService.choose(singleList, 1)
      significant.length shouldBe 1
    }

    "choose zero number of points from a single-element-list when zero to choose" in {
      val significantZero = SignificantPointsService.choose(singleList, 0)
      significantZero.length shouldBe 0
    }
  }

}
