package ffeltrinelli.textualclock.domain.words.english

import ffeltrinelli.textualclock.domain.Randomizer
import ffeltrinelli.textualclock.domain.words.FillerWord

class EnglishFillerWord(length: Int, randomizer: Randomizer): FillerWord {

    init {
        require(length > 0) { "filler length should be > 0" }
    }

    private val text = CharArray(length) {_ -> randomizer.englishLowercaseLetter()}.concatToString()

    override fun text() = text

    override fun toString() = text

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EnglishFillerWord) return false

        return text == other.text
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }
}
