package adventofcode

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.reflect.ClassTag

object Day1 {
  val day: String = this.getClass.getSimpleName.replace("Day", "").replace("$", "")
  val example = false

  def main(args: Array[String]): Unit = {
    val fileInput = readFile()
    val elvenCalorieLists = mapLines(fileInput)
    val elvenCaloriesSummed = elvenCalorieLists.map(calorieList => calorieList.sum)
    println(elvenCaloriesSummed.sorted.takeRight(3).sum)
    }

  def readFile(): List[String] = {
    var filename = s"src/main/resources/input_day${day}"
    if(example) filename += "_example"
    val source = Source.fromFile(filename)
    val list = source.getLines().toList
    source.close()
    list
  }

  def mapLines(lines: List[String]): List[List[Int]] = {
    val elvenCalorieLists = new ListBuffer[List[Int]]()
    var currentList = new ListBuffer[Int]()
    lines.foreach(line => {
      if(line.isBlank) {
        elvenCalorieLists.addOne(currentList.toList)
        currentList = new ListBuffer[Int]()
      }
      else {
        currentList.addOne(line.toInt)
      }
    })
    elvenCalorieLists.addOne(currentList.toList)
    elvenCalorieLists.toList
  }
}
