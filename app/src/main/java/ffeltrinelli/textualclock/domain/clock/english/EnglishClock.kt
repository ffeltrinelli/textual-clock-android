package ffeltrinelli.textualclock.domain.clock.english

import ffeltrinelli.textualclock.domain.Randomizer
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
class EnglishClock(randomizer: Randomizer, englishTime: EnglishTime): ClockMatrix(generateWords(randomizer, englishTime)) {
    companion object {
        private val UNPROCESSED_ENGLISH_ROWS: List<ClockRow> = listOf(
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
        // TODO add Dependency Injection
        private val RANDOMIZER = Randomizer(Random)
        private val ENGLISH_TIME = EnglishTime()
        val INSTANCE = EnglishClock(RANDOMIZER, ENGLISH_TIME)

        fun generateWords(randomizer: Randomizer, englishTime: EnglishTime): List<ClockRow> {
            val rows = selectWordsBasedOnTime(UNPROCESSED_ENGLISH_ROWS, englishTime)
            return normalizeLength(rows, randomizer)
        }

        private fun selectWordsBasedOnTime(rows: List<ClockRow>, englishTime: EnglishTime): List<ClockRow> {
            // TODO implement words selection logic
            return rows
        }

        /**
         * Add filler characters so that all rows have the same length of the longest row
         */
        private fun normalizeLength(rows: List<ClockRow>, randomizer: Randomizer): List<ClockRow> {
            val maxRowLength = rows.maxOf { it.length }
            val normalizedRows: List<ClockRow> = rows.map { row ->
                if (row.length < maxRowLength) row.addFillers(
                    maxRowLength - row.length,
                    randomizer
                ) else row
            }
            return normalizedRows
        }
    }
}

/**
 * Add filler characters of the given length to this row.
 * Filler characters are un-selected.
 */
fun ClockRow.addFillers(length: Int, randomizer: Randomizer): ClockRow {
    // TODO distribute filler length between row's words randomly
    return ClockRow(words + Filler(length, randomizer).toUnselected())
}
