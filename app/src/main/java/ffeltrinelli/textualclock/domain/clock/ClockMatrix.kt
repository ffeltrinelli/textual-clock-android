package ffeltrinelli.textualclock.domain.clock

/**
 * A clock made of a matrix of words.
 * All rows must have the same characters length.
 */
abstract class ClockMatrix(
    val rows: List<ClockRow>
) {
    init {
        require(rows.isNotEmpty()) { "clock cannot have zero rows" }
        require(rows.all { it.length == rows.first().length }) { "all rows must have the same length" }
    }

    /**
     * Number of characters in each row.
     */
    val rowLength = rows.first().length
}
