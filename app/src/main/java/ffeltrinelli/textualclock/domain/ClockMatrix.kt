package ffeltrinelli.textualclock.domain

import ffeltrinelli.textualclock.domain.words.Connector
import ffeltrinelli.textualclock.domain.words.Hour
import ffeltrinelli.textualclock.domain.words.Minutes
import ffeltrinelli.textualclock.domain.words.Word
import kotlin.random.Random

/**
 * A matrix of rows of words.
 * All rows have the same length.
 */
class ClockMatrix(
    val rows: List<WordsRow>
) {
    class Builder(private val generator: RandomGenerator) {
        private val rows: MutableList<WordsRow> = mutableListOf()

        fun addRow(vararg row: Word) = apply { rows.add(WordsRow(row.toList(), generator)) }

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
        private val GENERATOR = RandomGenerator(Random)

        val ENGLISH_CLOCK = Builder(GENERATOR)
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
