package ffeltrinelli.textualclock.domain.words.english

import ffeltrinelli.textualclock.domain.Randomizer
import ffeltrinelli.textualclock.domain.words.FillerWord

class EnglishFillerWord(length: Int, randomizer: Randomizer): FillerWord {
    private val text = CharArray(length) {_ -> randomizer.englishLowercaseLetter()}.concatToString()

    override fun text() = text

    override fun toString() = text
}
