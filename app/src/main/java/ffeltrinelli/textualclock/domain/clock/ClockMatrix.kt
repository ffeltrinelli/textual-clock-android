package ffeltrinelli.textualclock.domain.clock

import ffeltrinelli.textualclock.domain.words.Word

/**
 * A clock made of a matrix of words.
 * All rows must have the same characters length.
 */
abstract class ClockMatrix(
    val rows: List<WordsRow>
) {
    init {
        require(rows.isNotEmpty()) { "clock cannot have zero rows" }
        require(rows.all { it.length == rows.first().length }) { "all rows must have the same length" }
    }

    /**
     * Number of characters in each row.
     */
    val rowLength = rows.first().length

    /**
     * Flattened list of all words from all rows, in row order.
     * Can contain duplicates.
     */
    val words: List<Word> = rows.flatMap { it.words }
}
