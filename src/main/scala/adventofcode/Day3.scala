package adventofcode

object Day3 extends AocBase {

  def main(args: Array[String]): Unit = {
    part1(() => fileInput.map(str => str.splitAt(str.length / 2)).map(evaluateRucksacks1).map(toValue).sum)
    part2(() => fileInput.sliding(3, 3).map(evaluateRucksacks2).map(toValue).sum)
    }

  def toValue(char:Char): Int =  if char.isUpper then char - 'A' + 27 else char - 'a' + 1

  def evaluateRucksacks1(rucksacks: (String, String)): Char = {
    val overlaps = rucksacks._1.intersect(rucksacks._2)
    overlaps.head
  }

  def evaluateRucksacks2(rucksacks: List[String]): Char = {
    val overlaps = rucksacks.head.intersect(rucksacks(1)).intersect(rucksacks(2))
    overlaps(0)
  }
}
