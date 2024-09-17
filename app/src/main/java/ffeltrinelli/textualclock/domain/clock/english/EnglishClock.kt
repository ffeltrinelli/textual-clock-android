package ffeltrinelli.textualclock.domain.clock.english

import ffeltrinelli.textualclock.domain.Randomizer
import ffeltrinelli.textualclock.domain.clock.TextualClock
import ffeltrinelli.textualclock.domain.clock.ClockRow
import ffeltrinelli.textualclock.domain.clock.ClockRow.Companion.unselectedRowFrom
import ffeltrinelli.textualclock.domain.words.Word
import ffeltrinelli.textualclock.domain.words.english.Connector
import ffeltrinelli.textualclock.domain.words.english.Filler
import ffeltrinelli.textualclock.domain.words.english.Hour
import ffeltrinelli.textualclock.domain.words.english.Minutes

/**
 * A textual clock with english words.
 */
class EnglishClock private constructor(
    private val englishTime: EnglishTime,
    rows: List<ClockRow>
) : TextualClock(rows) {

    constructor(randomizer: Randomizer, englishTime: EnglishTime, wordsPerRoW: Int) : this(
        englishTime, generateWords(randomizer, englishTime, wordsPerRoW)
    )

    /**
     * Builds a new instance where the words are the same but selected
     * based on current time.
     */
    fun updateWordsSelection(): EnglishClock = EnglishClock(englishTime, selectWordsBasedOnTime(rows, englishTime))

    companion object {
        /**
         * All english words ordered in a way that
         * they can form meaningful sentences regarding time.
         */
        val ENGLISH_WORDS_ORDERED: List<Word> = listOf(
            Connector.IT_IS, Minutes.QUARTER, Minutes.TWENTY, Minutes.FIVE,
            Minutes.HALF, Minutes.TEN, Connector.TO, Connector.PAST, Hour.NINE,
            Hour.ONE, Hour.SIX, Hour.THREE, Hour.FOUR, Hour.FIVE, Hour.TWO,
            Hour.EIGHT, Hour.ELEVEN, Hour.SEVEN, Hour.TWELVE,
            Hour.TEN, Connector.O_CLOCK
        )

        fun generateWords(randomizer: Randomizer, englishTime: EnglishTime, wordsPerRoW: Int): List<ClockRow> {
            var rows = chunkWordsIntoRows(wordsPerRoW)
            rows = normalizeLength(rows, randomizer)
            return selectWordsBasedOnTime(rows, englishTime)
        }

        private fun chunkWordsIntoRows(wordsPerRow: Int): List<ClockRow> =
            ENGLISH_WORDS_ORDERED.chunked(wordsPerRow) { wordsInRow ->
                unselectedRowFrom(wordsInRow)
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

        private fun selectWordsBasedOnTime(rows: List<ClockRow>, englishTime: EnglishTime): List<ClockRow> {
            val currentTime = englishTime.currentTime()
            val currentTimeWords = englishTime.convertToWords(currentTime)
            return rows.map { it.selectWordsIn(currentTimeWords) }
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
