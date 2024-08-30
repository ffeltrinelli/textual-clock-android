package ffeltrinelli.textualclock.domain.clock

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
}
