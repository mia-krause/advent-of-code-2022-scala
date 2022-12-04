package adventofcode

import adventofcode.extensions.SeqExtensions._

object Day3 extends AocBase[Int] {

  def main(args: Array[String]): Unit = {
    part1(157, (input: List[String]) => input.map(str => str.splitAt(str.length / 2)).map(evaluateRucksacks1).map(toValue).sum)
    part2(70, (input: List[String]) => input.sliding(3, 3).map(evaluateRucksacks2).map(toValue).sum)
    }

  def toValue(char:Char): Int =  if char.isUpper then char - 'A' + 27 else char - 'a' + 1

  def evaluateRucksacks1(rucksacks: (String, String)): Char = {
    val overlaps = rucksacks._1 & rucksacks._2
    overlaps.head
  }

  def evaluateRucksacks2(rucksacks: List[String]): Char = {
    val overlaps = rucksacks.head & rucksacks(1) & rucksacks(2)
    overlaps(0)
  }
}
