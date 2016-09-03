package kaa.lighthouse


import org.scalatest._

class UtilTest extends FlatSpec with Matchers {
  behavior of "Util"

  val diagonal = Set(
    Point(6,6),
    Point(5,5),
    Point(4,4),
    Point(3,3),
    Point(2,2),
    Point(1,1))
  val vertical = Set(
    Point(1,1),
    Point(1,2),
    Point(1,3),
    Point(1,4),
    Point(1,5))
  val horizontal = Set(
    Point(5,1),
    Point(4,1),
    Point(3,1),
    Point(2,1),
    Point(1,1))

  it should "calculate line for any two points" in {
    import Util.line

    val line1 = Set(
      Point(12,2),
      Point(11,2),
      Point(10,3),
      Point(9,3),
      Point(8,3),
      Point(7,4),
      Point(6,4),
      Point(5,5),
      Point(4,5),
      Point(3,5),
      Point(2,6),
      Point(1,6))

    val line2 = Set(
      Point(6,4),
      Point(5,4),
      Point(4,3),
      Point(3,3),
      Point(2,2),
      Point(1,2),
      Point(0,1))

    val line3 = Set(
      Point(4,1),
      Point(4,2),
      Point(5,3),
      Point(5,4),
      Point(5,5),
      Point(5,6),
      Point(6,7),
      Point(6,8),
      Point(6,9))

    assert(line(Point(1, 6), Point(12, 2)).toSet === line1)
    assert(line(Point(0, 1), Point(6, 4)).toSet === line2)
    assert(line(Point(6, 4), Point(0, 1)).toSet === line2)
    assert(line(Point(4, 1), Point(6, 9)).toSet == line3)
    assert(line(Point(1, 1), Point(6, 6)).toSet === diagonal)
    assert(line(Point(6, 6), Point(1, 1)).toSet === diagonal)
    assert(line(Point(1, 1), Point(5, 1)).toSet === horizontal)
    assert(line(Point(1, 1), Point(1, 5)).toSet === vertical)
  }

  it should "trace ray between any two points" in {
    import Util.ray

    val ray1 = List(
      Point(12,2),
      Point(11,2),
      Point(11,3),
      Point(10,3),
      Point(9,3),
      Point(8,3),
      Point(8,4),
      Point(7,4),
      Point(6,4),
      Point(5,4),
      Point(5,5),
      Point(4,5),
      Point(3,5),
      Point(2,5),
      Point(2,6),
      Point(1,6))

    val ray2 = List(
      Point(6,4),
      Point(5,4),
      Point(5,3),
      Point(4,3),
      Point(3,3),
      Point(3,2),
      Point(2,2),
      Point(1,2),
      Point(1,1),
      Point(0,1))

    assert(ray(Point(1, 6), Point(12, 2)) === ray1)
    assert(ray(Point(0, 1), Point(6, 4)) === ray2)
    assert(ray(Point(6, 4), Point(0, 1)) === ray2)
    assert(ray(Point(1, 1), Point(6, 6)).toSet === diagonal)
    assert(ray(Point(6, 6), Point(1, 1)).toSet === diagonal)
    assert(ray(Point(1, 1), Point(5, 1)).toSet === horizontal)
    assert(ray(Point(1, 1), Point(1, 5)).toSet === vertical)
  }

}
