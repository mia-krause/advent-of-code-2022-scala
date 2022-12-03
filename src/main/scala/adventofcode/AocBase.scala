package adventofcode

import scala.io.Source
import scala.reflect.ClassTag

trait AocBase {
  val day: String = this.getClass.getSimpleName.replace("Day", "").replace("$", "")
  val fileInput: List[String] = readFile()

  def readFile(example: Boolean = false): List[String] = {
    var filename = s"src/main/resources/input_day${day}"
    if example then filename += "_example"
    val source = Source.fromFile(filename)
    val list = source.getLines().toList
    source.close()
    list
  }
  
  def part1(func: () => _) = solve("Part 1", func)
  def part2(func: () => _) = solve("Part 2", func)
  
  def solve(msg: String, func: () => _): Unit = {
    val result = func()
    println(s"$msg : $result")
  }
}
