package ffeltrinelli.textualclock.domain.clock

import ffeltrinelli.textualclock.domain.words.Word

/**
 * A clock made of a matrix of words.
 * All rows must have the same characters length.
 */
abstract class ClockMatrix(
    // TODO check all rows have same length or throw
    val rows: List<WordsRow>
) {
    /**
     * Number of characters in each row.
     */
    val rowLength = rows.first().length

    /**
     * Flattened list of all words from all rows.
     */
    val words: List<Word> = rows.flatMap { it.words }
}
