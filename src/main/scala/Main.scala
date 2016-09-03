package kaa.lighthouse

import scala.annotation.tailrec
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.input.KeyType.{
  ArrowDown,
  ArrowLeft,
  ArrowRight,
  ArrowUp}


object Main extends App {

  var posX = 0
  var posY = 0

  @tailrec def loop() {
    val tg = screen.newTextGraphics()
    tg.fill('.')
    tg.putString(posX, posY, "@")
    tg.drawLine(4,1,6,9,'*')
    Util.line(Point(7,1), Point(9,9)).foreach { p =>
      tg.setCharacter(p.toTerminalPosition, '*')
    }
    /*
    Util.ray(Point(7,1), Point(9,9)).foreach { p =>
      tg.setCharacter(p.toTerminalPosition, '*')
    }
    */
    screen.refresh()

    screen.readInput().getKeyType() match {
      case ArrowUp => posY = posY - 1
      case ArrowDown => posY = posY + 1
      case ArrowRight => posX = posX + 1
      case ArrowLeft => posX = posX - 1
    }
    loop()
  }

  val terminal = new DefaultTerminalFactory().createTerminal()
  val screen = new TerminalScreen(terminal)
  screen.startScreen()
  screen.setCursorPosition(null)
  loop()
  screen.stopScreen()
}
