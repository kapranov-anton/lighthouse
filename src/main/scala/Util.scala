package kaa.lighthouse

import com.googlecode.lanterna.TerminalPosition


case class Point(x: Int, y: Int) {
  def toTerminalPosition = new TerminalPosition(x, y)
  def swap() = Point(y, x)
}

object Util {
  def trace(x0: Int, y0: Int, x1: Int, y1: Int): List[Point] = {
    val dx = x1 - x0
    val dy = y1 - y0
    val dxAbs = dx.abs
    val dyAbs = dy.abs
    val n = 1 + dxAbs + dyAbs
    val incX = dx.signum
    val incY = dy.signum
    val dx2 = dxAbs * 2;
    val dy2 = dyAbs * 2;
    val acc = (List[Point](), x0, y0, dxAbs - dyAbs)
    (n to 1 by -1).foldLeft(acc) {
      case ((prevPoints, x, y, error), _) =>
        val points = Point(x, y) :: prevPoints

        if (error > 0) (points, x + incX, y,        error - dy2)
        else           (points, x,        y + incY, error + dx2)
    }._1
  }

  def bresenham(x0: Int, y0: Int, x1: Int, y1: Int): List[Point] = {
    val dx = x1 - x0
    val dy = y1 - y0
    val dxAbs = dx.abs
    val dyAbs = dy.abs
    val incY = dy.signum
    val initError = 0.0
    val acc = (List[Point](), y0, initError)

    val (resultLine, _, _) =
      (x0 to (x1 - 1)).foldLeft(acc) {
        case ((prevPoints, y, prevError), x) =>
          val points = Point(x, y) :: prevPoints
          val error = prevError + dyAbs

          if (2 * error >= dxAbs) {
            (points, y + incY, error - dxAbs)
          } else {
            (points, y, error)
          }
      }

    Point(x1, y1) :: resultLine
  }


  def plot(func: (Int, Int, Int, Int) => List[Point])
          (from: Point, to: Point): List[Point] = {
    val (Point(x0, y0), Point(x1, y1)) = (from, to)

    val dx = x1 - x0
    val dy = y1 - y0
    val delta = dy.toFloat / dx

    // vertical
    if (x0 == x1) {
      (y0 to y1).map(Point(x0, _)).toList
    // horizontal
    } else if (y0 == y1) {
      (x0 to x1).map(Point(_, y0)).toList
    // diagonal
    } else if (delta.abs == 1) {
      (if (x0 < x1) {
        ((x0 to x1), (y0 to y1))
      } else {
        ((x1 to x0), (y1 to y0))
      }).zipped.map(Point(_, _)).toList
    } else {
      if (delta < 1) {
        if (x0 < x1) {
          func(x0, y0, x1, y1)
        } else {
          func(x1, y1, x0, y0)
        }
      } else {
        (if (y0 < y1) {
          println(111111111)
          func(y0, x0, y1, x1)
        } else {
          func(y1, x1, y0, x0)
        }).map(_.swap)
      }
    }
  }

  def ray = plot(trace) _
  def line = plot(bresenham) _
}
