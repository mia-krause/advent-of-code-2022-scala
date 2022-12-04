package adventofcode.extensions

import adventofcode.extensions.SeqExtensions.toTuple

object StringExtensions {

  extension(string: String) {

    def splitIntoTuple(separator: Char): (String, String) = {
      string.split(separator).toSeq.toTuple
    }

    def splitIntoTuple(regex: String): (String, String) = {
      string.split(regex).toSeq.toTuple
    }
  }

}
