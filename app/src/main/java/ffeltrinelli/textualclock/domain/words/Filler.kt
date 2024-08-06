package ffeltrinelli.textualclock.domain.words

import ffeltrinelli.textualclock.domain.RandomGenerator

class Filler(length: Int, generator: RandomGenerator): Word {
    private val text = CharArray(length) {_ -> generator.nextLetter()}.concatToString()

    override fun text() = text
}
