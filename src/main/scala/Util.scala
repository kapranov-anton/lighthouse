package kaa.lighthouse


object Util {
  type Point = (Int, Int)

  def trace(dx: Int, dy: Int, delta: Float, x0: Int, y0: Int, x1: Int, y1: Int): List[Point] = {
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
        val points = (x, y) :: prevPoints

        if (error > 0) (points, x + incX, y,        error - dy2)
        else           (points, x,        y + incY, error + dx2)
    }._1
  }

  def bresenham(dx: Int, dy: Int, delta: Float, x0: Int, y0: Int, x1: Int, y1: Int): List[Point] = {
    val deltaAbs = delta.abs
    val incY = dy.signum
    val acc = (List[Point](), y0, -1.0)
    val (resultLine, _, _) =
      (x0 to (x1 - 1)).foldLeft(acc) {
        case ((prevPoints, y, prevError), x) =>
          val points = (x, y) :: prevPoints
          val error = prevError + deltaAbs

          if (error >= 0) (points, y + incY, error - 1)
          else            (points, y,        error)
      }

    (x1, y1) :: resultLine
  }


  def plot(func: (Int, Int, Float, Int, Int, Int, Int) => List[Point])
          (from: Point, to: Point): List[Point] = {
    val ((x0, y0), (x1, y1)) =
      if (from._1 <= to._1) {
        (from, to)
      } else {
        (to, from)
      }

    val dx = x1 - x0
    val dy = y1 - y0
    val delta = dy.toFloat / dx

    if (x0 == x1) {
      (y0 to y1).map((x0, _)).toList
    } else if (y0 == y1) {
      (x0 to x1).map((_, y0)).toList
    } else if (delta.abs == 1) {
      (x0 to x1).zip(y0 to y1).toList
    } else {
      func(dx, dy, delta, x0, y0, x1, y1)
    }
  }

  def ray = plot(trace) _
  def line = plot(bresenham) _
}
