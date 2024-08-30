package ffeltrinelli.textualclock.domain.words.english

import ffeltrinelli.textualclock.domain.RandomGenerator
import ffeltrinelli.textualclock.domain.words.Word

class Filler(length: Int, generator: RandomGenerator): Word {
    private val text = CharArray(length) {_ -> generator.nextLetter()}.concatToString()

    override fun text() = text

    override fun toString() = text
}
