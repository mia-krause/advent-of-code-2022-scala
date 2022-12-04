package adventofcode

import scala.collection.immutable.Set

object Day4 extends AocBase[Int] {
  def main(args: Array[String]): Unit = {
    part1(2, (input: List[String]) => toAssignedSections(input).map((a, b) => a.subsetOf(b) || b.subsetOf(a)).count(identity))
    part2(4, (input: List[String]) => toAssignedSections(input).map((a, b) => a & b).count(_.nonEmpty))
  }

  def toAssignedSections(input: List[String]): List[(Set[Int], Set[Int])] = {
    input.map(_.split(',')).map(arr => (itemize(arr(0)), itemize(arr(1))))
  }

  def itemize(sectionWithMinus: String): Set[Int] = {
    val pairs = sectionWithMinus.split('-')
    Range.inclusive(pairs(0).toInt, pairs(1).toInt).toSet
  }



}
