package ffeltrinelli.textualclock.domain

import ffeltrinelli.textualclock.domain.words.Filler
import ffeltrinelli.textualclock.domain.words.Word

/**
 * Words in a row.
 */
data class WordsRow(val words: List<Word>, private val generator: RandomGenerator) {
    /**
     * Sum of the length of all the words of this row.
     */
    val length = words.sumOf { it.length() }

    /**
     * Add filler elements of the given length to this row.
     */
    fun addFillers(length: Int): WordsRow {
        // TODO distribute filler length between row's words randomly
        return WordsRow(words + Filler(length, generator), generator)
    }
}

