package ffeltrinelli.textualclock

/**
 * Words in a row.
 */
data class WordsRow(val words: List<Word>) {
    /**
     * Sum of the length of all the words of this row.
     */
    val length = words.sumOf { it.length() }

    /**
     * Add filler elements of the given length to this row.
     */
    fun addFillers(length: Int): WordsRow {
        // TODO distribute filler length between row's words randomly
        return WordsRow(words + Filler(length))
    }
}

/**
 * A matrix of rows of words.
 * All rows have the same length.
 */
data class ClockMatrix(
    val rows: List<WordsRow>
) {
    class Builder {
        private val rows: MutableList<WordsRow> = mutableListOf()

        fun addRow(vararg row: Word) = apply { rows.add(WordsRow(row.toList())) }

        /**
         * Build the matrix adding filler elements so that all rows have the same length of the
         * longest row.
         */
        fun normalizeAndBuild(): ClockMatrix {
            val maxRowLength = rows.maxOf { it.length }
            val normalizedRows: List<WordsRow> = rows.map { row ->
                if (row.length < maxRowLength) row.addFillers(maxRowLength - row.length) else row
            }
            return ClockMatrix(normalizedRows)
        }
    }

    val rowsLength = rows.first().length

    companion object {
        val ENGLISH_CLOCK = Builder()
            .addRow(Connector.IT_IS)
            .addRow(Minutes.QUARTER)
            .addRow(Minutes.TWENTY, Minutes.FIVE)
            .addRow(Minutes.HALF, Minutes.TEN, Connector.TO)
            .addRow(Connector.PAST, Hour.NINE)
            .addRow(Hour.ONE, Hour.SIX, Hour.THREE)
            .addRow(Hour.FOUR, Hour.FIVE, Hour.TWO)
            .addRow(Hour.EIGHT, Hour.ELEVEN)
            .addRow(Hour.SEVEN, Hour.TWELVE)
            .addRow(Hour.TEN, Connector.O_CLOCK)
            .normalizeAndBuild()
    }
}
