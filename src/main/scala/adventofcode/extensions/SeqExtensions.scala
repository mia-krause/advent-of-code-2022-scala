package adventofcode.extensions

import scala.annotation.targetName

object SeqExtensions {

  extension[T] (seq: Seq[T]) {
    def toTuple = (seq.head, seq(1))
    @targetName("intersect")
    def &(other: Seq[T]): Seq[T] = seq.intersect(other)
  }

}
