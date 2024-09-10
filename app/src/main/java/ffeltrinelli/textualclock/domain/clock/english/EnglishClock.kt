package ffeltrinelli.textualclock.domain.clock.english

import ffeltrinelli.textualclock.domain.RandomGenerator
import ffeltrinelli.textualclock.domain.clock.ClockMatrix
import ffeltrinelli.textualclock.domain.clock.ClockRow
import ffeltrinelli.textualclock.domain.clock.ClockRow.Companion.row
import ffeltrinelli.textualclock.domain.words.english.Connector
import ffeltrinelli.textualclock.domain.words.english.Filler
import ffeltrinelli.textualclock.domain.words.english.Hour
import ffeltrinelli.textualclock.domain.words.english.Minutes
import kotlin.random.Random

/**
 * A textual clock with english words.
 */
class EnglishClock(generator: RandomGenerator): ClockMatrix(initEnglishWords(generator)) {
    companion object {
        fun initEnglishWords(generator: RandomGenerator): List<ClockRow> {
            val rows: List<ClockRow> = listOf(
                row(Connector.IT_IS),
                row(Minutes.QUARTER),
                row(Minutes.TWENTY, Minutes.FIVE),
                row(Minutes.HALF, Minutes.TEN, Connector.TO),
                row(Connector.PAST, Hour.NINE),
                row(Hour.ONE, Hour.SIX, Hour.THREE),
                row(Hour.FOUR, Hour.FIVE, Hour.TWO),
                row(Hour.EIGHT, Hour.ELEVEN),
                row(Hour.SEVEN, Hour.TWELVE),
                row(Hour.TEN, Connector.O_CLOCK),
            )

            return normalizeLength(rows, generator)
        }

        /**
         * Add filler characters so that all rows have the same length of the longest row
         */
        private fun normalizeLength(rows: List<ClockRow>, generator: RandomGenerator): List<ClockRow> {
            val maxRowLength = rows.maxOf { it.length }
            val normalizedRows: List<ClockRow> = rows.map { row ->
                if (row.length < maxRowLength) row.addFillers(
                    maxRowLength - row.length,
                    generator
                ) else row
            }
            return normalizedRows
        }

        // TODO add Dependency Injection
        private val GENERATOR = RandomGenerator(Random)
        val INSTANCE = EnglishClock(GENERATOR)
    }
}

/**
 * Add filler characters of the given length to this row.
 * Filler characters are un-selected.
 */
fun ClockRow.addFillers(length: Int, generator: RandomGenerator): ClockRow {
    // TODO distribute filler length between row's words randomly
    return ClockRow(words + Filler(length, generator).toUnselected())
}
