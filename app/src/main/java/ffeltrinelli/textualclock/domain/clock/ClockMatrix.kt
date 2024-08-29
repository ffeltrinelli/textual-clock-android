package ffeltrinelli.textualclock.domain.clock

/**
 * A clock made of a matrix of words.
 * All rows must have the same characters length.
 */
abstract class ClockMatrix(
    // TODO check all rows have same length or throw
    val rows: List<WordsRow>
) {
    val rowsLength = rows.first().length
}
