package adventofcode

import scala.io.Source
import scala.reflect.ClassTag

trait AocBase[Result] {
  val day: String = this.getClass.getSimpleName.replace("Day", "").replace("$", "")

  def readFile(example: Boolean = false): List[String] = {
    var filename = s"src/main/resources/input_day${day}"
    if example then filename += "_example"
    val source = Source.fromFile(filename)
    val list = source.getLines().toList
    source.close()
    list
  }

  def part1(exampleResult: Result, func: List[String] => Result) = solve("Part 1", func, exampleResult)
  def part2(exampleResult: Result, func: List[String] => Result) = solve("Part 2", func, exampleResult)

  def solve(msg: String, func: List[String] => Result, expectedExampleResult: Result): Unit = {
    val exampleResult = func(readFile(true))
    assert(expectedExampleResult == exampleResult, s"Expected $expectedExampleResult, but calculated $exampleResult")
    val result = func(readFile(false))
    println(s"$msg: $result")
  }
}
