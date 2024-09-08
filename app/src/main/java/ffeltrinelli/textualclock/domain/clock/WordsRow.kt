package ffeltrinelli.textualclock.domain.clock

import ffeltrinelli.textualclock.domain.words.Word

/**
 * Words in a row.
 */
data class WordsRow(val words: List<Word>) {
    // TODO check that words is not empty

    /**
     * Total number of characters in this row.
     */
    val length = words.sumOf { it.length() }

    companion object {
        fun row(vararg row: Word) = WordsRow(row.toList())
    }
}
