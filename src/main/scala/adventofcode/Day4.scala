package adventofcode

import scala.collection.immutable.{Set, StringOps}
import adventofcode.extensions.TupleExtensions.*
import adventofcode.extensions.StringExtensions.*

object Day4 extends AocBase[Int] {
  def main(args: Array[String]): Unit = {
    part1(2, (input: List[String]) => toAssignedSections(input).map(containsSubset).count(identity))
    part2(4, (input: List[String]) => toAssignedSections(input).map(overlaps).count(identity))
  }

  def toAssignedSections(input: List[String]): List[(Set[Int], Set[Int])] = {
    input.map(_.splitIntoTuple(',')).map(tuple => tuple.applyToBoth(itemize))
  }

  def itemize(sectionWithMinus: String): Set[Int] = {
    sectionWithMinus.splitIntoTuple('-').applyToBoth(_.toInt).toRange.toSet
  }
}
