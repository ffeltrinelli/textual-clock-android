package ffeltrinelli.textualclock.domain.clock.english

import ffeltrinelli.textualclock.domain.clock.LocalClock
import ffeltrinelli.textualclock.domain.words.Word
import ffeltrinelli.textualclock.domain.words.english.Connector.IT_IS
import ffeltrinelli.textualclock.domain.words.english.Connector.O_CLOCK
import ffeltrinelli.textualclock.domain.words.english.Connector.PAST
import ffeltrinelli.textualclock.domain.words.english.Connector.TO
import ffeltrinelli.textualclock.domain.words.english.Hour
import ffeltrinelli.textualclock.domain.words.english.Minutes
import ffeltrinelli.textualclock.domain.words.english.Minutes.HALF
import ffeltrinelli.textualclock.domain.words.english.Minutes.QUARTER
import ffeltrinelli.textualclock.domain.words.english.Minutes.TWENTY
import java.time.LocalTime
import java.time.temporal.ChronoField
import javax.inject.Inject

/**
 * Helper to represent time as text in english.
 */
class EnglishTime @Inject constructor(
    private val localClock: LocalClock
) {

    fun currentTime(): LocalTime = localClock.now()

    @Suppress("MagicNumber")
    fun convertToWords(time: LocalTime): List<Word> {
        val amPmHour = time.get(ChronoField.CLOCK_HOUR_OF_AMPM)
        val hourWord = Hour.fromAmPmHour(amPmHour)
        val nextHourWord = Hour.fromAmPmHour((amPmHour % 12) + 1)

        return when (time.minute) {
            in 0..4 -> listOf(IT_IS, hourWord, O_CLOCK)
            in 5..9 -> listOf(IT_IS, Minutes.FIVE, PAST, hourWord)
            in 10..14 -> listOf(IT_IS, Minutes.TEN, PAST, hourWord)
            in 15..19 -> listOf(IT_IS, QUARTER, PAST, hourWord)
            in 20..24 -> listOf(IT_IS, TWENTY, PAST, hourWord)
            in 25..29 -> listOf(IT_IS, TWENTY, Minutes.FIVE, PAST, hourWord)
            in 30..34 -> listOf(IT_IS, HALF, PAST, hourWord)
            in 35..39 -> listOf(IT_IS, TWENTY, Minutes.FIVE, TO, nextHourWord)
            in 40..44 -> listOf(IT_IS, TWENTY, TO, nextHourWord)
            in 45..49 -> listOf(IT_IS, QUARTER, TO, nextHourWord)
            in 50..54 -> listOf(IT_IS, Minutes.TEN, TO, nextHourWord)
            in 55..59 -> listOf(IT_IS, Minutes.FIVE, TO, nextHourWord)
            else -> throw IllegalArgumentException("Minute of time $time is in illegal range")
        }
    }

    companion object {
        /**
         * English time always fixed to the given hour and minute.
         * To be used in unit tests and Compose Preview.
         */
        fun fixed(hour: Int, minute: Int) = EnglishTime { LocalTime.of(hour, minute) }
    }
}
