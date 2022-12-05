package adventofcode.utils

class Stack[A] {
  private var elements: List[A] = List.empty

  def push(x: A): Unit = elements = elements.prepended(x)

  def pushAsBlock(x: List[A]): Unit = elements = elements.prependedAll(x)

  def peek: A = elements.head

  def pop(): A = popAsBlock(1).head

  def popAsBlock(n: Int): List[A] =
    val currentTop = elements.take(n)
    elements = elements.takeRight(elements.length - n)
    currentTop

  override def toString: String = elements.mkString(" ")
}
