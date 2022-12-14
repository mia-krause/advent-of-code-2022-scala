package adventofcode.days

import adventofcode.AocBase
import adventofcode.utils.Stack

import scala.util.matching.Regex

object Day5 extends AocBase[String] {
  val movementRegex: Regex = raw"move (\d+) from (\d+) to (\d+)".r
  val crateRegex: Regex = raw"\[(\w)]".r

  def main(args: Array[String]): Unit = {
    part1("CMZ", (input: List[String]) => {
      val (stacks, movements) = parseInput(input)
      val resultingStacks = move(stacks, movements, 9000)
      resultingStacks.map(_.peek.content).mkString
    })
    part2("MCD", (input: List[String]) => {
      val (stacks, movements) = parseInput(input)
      val resultingStacks = move(stacks, movements, 9001)
      resultingStacks.map(_.peek.content).mkString
    })
  }


  def move(stacks: Array[Stack[Crate]], movements: List[Movement], crateMoverVersion: Int = 9000): Array[Stack[Crate]] = {
    crateMoverVersion match
      case 9000 => movements.foldLeft(stacks) { (currentStacks: Array[Stack[Crate]], movement: Movement) => move9000(currentStacks, movement) }
      case 9001 => movements.foldLeft(stacks) { (currentStacks: Array[Stack[Crate]], movement: Movement) => move9001(currentStacks, movement) }
  }

  def move9000(stacks: Array[Stack[Crate]], movement: Movement): Array[Stack[Crate]] = {
    for _ <- 0 until movement.n do {
      stacks(movement.to - 1).push(stacks(movement.from - 1).pop())
    }
    stacks
  }

  def move9001(stacks: Array[Stack[Crate]], movement: Movement): Array[Stack[Crate]] = {
    stacks(movement.to - 1).pushAsBlock(stacks(movement.from - 1).popAsBlock(movement.n))
    stacks
  }

  def parseInput(input: List[String]): (Array[Stack[Crate]], List[Movement]) = {
    val (crateLines: List[String], otherLines: List[String]) = input.span(_.contains('['))
    val movementLines = otherLines.filter(_.startsWith("move"))
    (parseStacks(crateLines, otherLines.head), parseMovements(movementLines))
  }

  def parseStacks(crateLines: List[String], stackLine: String): Array[Stack[Crate]] = {
    val numberOfStacks = stackLine.split(' ').filterNot(_.isBlank).map(_.toInt).max
    val lineArr = crateLines.map(line => parseCrates(line, numberOfStacks)).reverse
    val stacks: Array[Stack[Crate]] = Array.fill(numberOfStacks)(new Stack[Crate]())
    for i <- 0 until numberOfStacks do {
      lineArr.foreach(line => {
        line(i) match
          case Some(crate) => stacks(i).push(crate)
          case None =>
      })
    }
    stacks
  }

  def parseCrates(line: String, numberOfCrates: Int): Array[Option[Crate]] = {
    val arr = line.sliding(3, 4).map(string => string match
      case "   " => None
      case crateRegex(content) => Some(Crate(content.head))
    ).toArray
    arr.padTo(numberOfCrates, None)
  }

  def parseMovements(movementLines: List[String]): List[Movement] = {
    movementLines.map(str => str match
      case movementRegex(n, from, to) => Movement(n.toInt, from.toInt, to.toInt)
    )
  }

  case class Movement(n: Int, from: Int, to: Int)

  case class Crate(content: Char) {
    def this(string: String) = this(string.replace("[", "").replace("]", "").trim.head)

    override def toString: String = s"[$content]"
  }
}
