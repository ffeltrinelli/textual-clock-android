package ffeltrinelli.textualclock.domain.clock

import ffeltrinelli.textualclock.domain.words.Word

/**
 * Words in a row.
 */
data class WordsRow(val words: List<Word>) {
    /**
     * Sum of the length of all the words of this row.
     */
    val length = words.sumOf { it.length() }

    companion object {
        fun row(vararg row: Word) = WordsRow(row.toList())
    }
}
