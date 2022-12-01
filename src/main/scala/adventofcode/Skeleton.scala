package adventofcode

import scala.io.Source
import scala.reflect.ClassTag

object Skeleton {
  val day = this.getClass.getSimpleName.replace("Day", "").replace("$", "")
  val example = true

  def main(args: Array[String]): Unit = {
    val fileInput = readFile()
    }

  def readFile(): List[String] = {
    var filename = s"src/main/resources/input_day${day}"
    if(example) filename += "_example"
    val source = Source.fromFile(filename)
    val list = source.getLines().toList
    source.close()
    list
  }

  extension [T:ClassTag] (twoDimArr:Array[Array[T]]) {
    def mirrored: Array[Array[T]] = {
      var arr = Array.ofDim[T](twoDimArr.head.length, twoDimArr.length)
      for (i <- twoDimArr.indices) {
        for (j <- twoDimArr(i).indices){
          arr(j)(i) = twoDimArr(i)(j)
        }
      }
      arr
    }

    def print(twoDimesionalArr: Array[Array[T]]) : Unit = {
      twoDimesionalArr.foreach(oneDimesionalArr => oneDimesionalArr.mkString(""))
    }
  }
}
