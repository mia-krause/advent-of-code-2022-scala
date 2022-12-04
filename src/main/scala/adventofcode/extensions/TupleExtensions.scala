package adventofcode.extensions

import scala.collection.immutable.Set

object TupleExtensions {

  extension[T] (tuple: (Set[T], Set[T])) {
    def overlaps: Boolean = (tuple._1 & tuple._2).nonEmpty
    def containsSubset: Boolean = tuple._1.subsetOf(tuple._2) || tuple._2.subsetOf(tuple._1)
  }

  extension[T] (tuple: (T, T)) {
    def applyToBoth[A](function: T => A): (A, A) = (function.apply(tuple._1), function.apply(tuple._2))
    def first: T = tuple._1
    def second: T = tuple._2
  }

  extension (tuple: (Int, Int)) {
    def toRange = Range.inclusive(tuple.first, tuple.second)
  }

}
