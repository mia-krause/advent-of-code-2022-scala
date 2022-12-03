package adventofcode

import scala.io.Source
import scala.reflect.ClassTag

object Day2 extends AocBase[Int] {
  val trumpsOver: Map[Sign, Sign] = Map(Rock -> Scissors, Paper -> Rock, Scissors -> Paper)
  val losesTo: Map[Sign, Sign] = Map(Rock -> Paper, Paper -> Scissors, Scissors -> Rock)

  object Sign {
    def createElfSign(char: Char): Sign = char match
      case 'A' => Rock
      case 'B' => Paper
      case 'C' => Scissors

    def createMySign(char: Char): Sign = char match
      case 'X' => Rock
      case 'Y' => Paper
      case 'Z' => Scissors

    def createMyReactiveSign(char: Char, sign: Sign): Sign = char match
      case 'X' => trumpsOver(sign)
      case 'Y' => sign
      case 'Z' => losesTo(sign)
  }

  sealed abstract class Sign(val value: Int) {
    def evaluate(sign: Sign): Int = {
      if this.equals(sign) then return DRAW
      if trumpsOver(this).eq(sign) then return WON
      return LOST
    }
  }
  case object Rock extends Sign(1)
  case object Paper extends Sign(2)
  case object Scissors extends Sign(3)

  val LOST = 0
  val DRAW = 3
  val WON = 6


  def main(args: Array[String]): Unit = {
    val lines = readFile()
    val signsPerRound = parseLinesPart2(lines)
    val valueSums: (Int, Int) = signsPerRound.map((elfSign:Sign, mySign: Sign) => (elfSign.value, mySign.value)).fold(0, 0)((x, y) => (x._1 + y._1, x._2 + y._2))
    val results:List[Int] = signsPerRound.map((elfSign, mySign ) => mySign.evaluate(elfSign))
    val overallResult = results.sum + valueSums._2
    println(overallResult)
  }

  def parseLinesPart1(lines: List[String]): List[(Sign, Sign)] = {
    lines.map(_.toCharArray).map(arr => (Sign.createElfSign(arr(0)), Sign.createMySign(arr(2))))
  }

  def parseLinesPart2(lines: List[String]): List[(Sign, Sign)] = {
    lines.map(_.toCharArray).map(arr => (Sign.createElfSign(arr(0)), Sign.createMyReactiveSign(arr(2), Sign.createElfSign(arr(0)))))
  }
}
