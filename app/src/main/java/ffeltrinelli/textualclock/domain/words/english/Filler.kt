package ffeltrinelli.textualclock.domain.words.english

import ffeltrinelli.textualclock.domain.Randomizer
import ffeltrinelli.textualclock.domain.words.Word

class Filler(length: Int, randomizer: Randomizer): Word {
    private val text = CharArray(length) {_ -> randomizer.nextLetter()}.concatToString()

    override fun text() = text

    override fun toString() = text
}
