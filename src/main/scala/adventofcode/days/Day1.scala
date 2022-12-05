package adventofcode.days

import adventofcode.AocBase

object Day1 extends AocBase[Int] {
  def main(args: Array[String]): Unit = {
    val fileInput = readFile()
    val elvenCalorieLists = mapLines(fileInput)
    val elvenCaloriesSummed = elvenCalorieLists.map(_.sum)
    println("Part 1: " + elvenCaloriesSummed.max)
    println("Part 2: " + elvenCaloriesSummed.sorted.takeRight(3).sum)
  }

  def mapLines(lines: List[String]): List[List[Int]] = {
    lines.mkString(",").split(",,").map(_.split(',').map(_.toInt).toList).toList
  }
}
