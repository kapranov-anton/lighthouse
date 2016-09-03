package kaa.lighthouse


import org.scalatest._

class UtilTest extends FlatSpec with Matchers {
  behavior of "Util"

  val diagonal = Set((6,6), (5,5), (4,4), (3,3), (2,2), (1,1))
  val vertical = Set((1,1), (1,2), (1,3), (1,4), (1,5))
  val horizontal = Set((5,1), (4,1), (3,1), (2,1), (1,1))

  it should "calculate line for any two points" in {
    import Util.line

    val line1 = Set((12,2), (11,3), (10,3), (9,4), (8,4), (7,4), (6,5), (5,5), (4,5), (3,6), (2,6), (1,6))
    val line2 = Set((6,4), (5,3), (4,3), (3,2), (2,2), (1,1), (0,1))
    assert(line((1, 6), (12, 2)).toSet === line1)
    assert(line((0, 1), (6, 4)).toSet === line2)
    assert(line((6, 4), (0, 1)).toSet === line2)
    assert(line((1, 1), (6, 6)).toSet === diagonal)
    assert(line((6, 6), (1, 1)).toSet === diagonal)
    assert(line((1, 1), (5, 1)).toSet === horizontal)
    assert(line((1, 1), (1, 5)).toSet === vertical)
  }

  it should "trace ray between any two points" in {
    import Util.ray

    val ray1 = List((12,2), (11,2), (11,3), (10,3), (9,3), (8,3), (8,4), (7,4), (6,4), (5,4), (5,5), (4,5), (3,5), (2,5), (2,6), (1,6))
    val ray2 = List((6,4), (5,4), (5,3), (4,3), (3,3), (3,2), (2,2), (1,2), (1,1), (0,1))

    assert(ray((1, 6), (12, 2)) === ray1)
    assert(ray((0, 1), (6, 4)) === ray2)
    assert(ray((6, 4), (0, 1)) === ray2)
    assert(ray((1, 1), (6, 6)).toSet === diagonal)
    assert(ray((6, 6), (1, 1)).toSet === diagonal)
    assert(ray((1, 1), (5, 1)).toSet === horizontal)
    assert(ray((1, 1), (1, 5)).toSet === vertical)
  }

}
