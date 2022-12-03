package adventofcode

import scala.io.Source
import scala.reflect.ClassTag

object Day3 {
  val day = this.getClass.getSimpleName.replace("Day", "").replace("$", "")
  val example = false

  def main(args: Array[String]): Unit = {
    val fileInput = readFile()
    val overlappingItems = fileInput.map(str => str.splitAt(str.length / 2)).map(evaluateRucksacks1)
    val badges = fileInput.sliding(3, 3).map(evaluateRucksacks2)

    println(badges.map(toValue).sum)
    }

  def toValue(char:Char) =  if char.isUpper then char - 'A' + 27 else char - 'a' + 1

  def evaluateRucksacks1(rucksacks: (String, String)): Char = {
    val overlaps = rucksacks._1.intersect(rucksacks._2)
    overlaps.head
  }

  def evaluateRucksacks2(rucksacks: List[String]): Char = {
    val overlaps = rucksacks.head.intersect(rucksacks(1)).intersect(rucksacks(2))
    overlaps(0)
  }

  def readFile(): List[String] = {
    var filename = s"src/main/resources/input_day${day}"
    if(example) filename += "_example"
    val source = Source.fromFile(filename)
    val list = source.getLines().toList
    source.close()
    list
  }

}
