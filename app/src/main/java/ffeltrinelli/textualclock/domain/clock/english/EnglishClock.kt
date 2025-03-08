package ffeltrinelli.textualclock.domain.clock.english

import ffeltrinelli.textualclock.domain.clock.ClockConfig
import ffeltrinelli.textualclock.domain.clock.TextualClock
import ffeltrinelli.textualclock.domain.clock.ClockRow
import ffeltrinelli.textualclock.domain.clock.ClockRow.Companion.unselectedRowFrom
import ffeltrinelli.textualclock.domain.clock.fill.ClockRowFiller
import ffeltrinelli.textualclock.domain.words.Word
import ffeltrinelli.textualclock.domain.words.english.Connector
import ffeltrinelli.textualclock.domain.words.english.Hour
import ffeltrinelli.textualclock.domain.words.english.Minutes

/**
 * A textual clock with english words.
 */
class EnglishClock private constructor(
    private val englishTime: EnglishTime,
    rows: List<ClockRow>
) : TextualClock(rows) {

    constructor(rowFiller: ClockRowFiller, englishTime: EnglishTime, clockConfig: ClockConfig) : this(
        englishTime, generateWords(rowFiller, englishTime, clockConfig)
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
            Connector.IT_IS, Minutes.HALF, Minutes.QUARTER, Minutes.TEN,
            Minutes.TWENTY, Minutes.FIVE, Connector.TO, Connector.PAST, Hour.NINE,
            Hour.ONE, Hour.SIX, Hour.THREE, Hour.FOUR, Hour.FIVE, Hour.TWO,
            Hour.EIGHT, Hour.ELEVEN, Hour.SEVEN, Hour.TWELVE,
            Hour.TEN, Connector.O_CLOCK
        )

        private fun generateWords(
            rowFiller: ClockRowFiller,
            englishTime: EnglishTime,
            clockConfig: ClockConfig
        ): List<ClockRow> {
            var rows = chunkWordsIntoRows(clockConfig.wordsPerRoW)
            rows = normalizeLength(rows, rowFiller)
            return selectWordsBasedOnTime(rows, englishTime)
        }

        private fun chunkWordsIntoRows(wordsPerRow: Int): List<ClockRow> =
            ENGLISH_WORDS_ORDERED.chunked(wordsPerRow) { wordsInRow ->
                unselectedRowFrom(wordsInRow)
            }

        /**
         * Add filler characters so that all rows have the same length of the longest row
         */
        private fun normalizeLength(rows: List<ClockRow>, rowFiller: ClockRowFiller): List<ClockRow> {
            val maxRowLength = rows.maxOf { it.length }
            val normalizedRows: List<ClockRow> = rows.map { row ->
                if (row.length < maxRowLength) rowFiller.fillRow(
                    row = row,
                    fillersNum = maxRowLength - row.length,
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
