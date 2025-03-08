package ffeltrinelli.textualclock.domain.clock.english

import assertk.assertThat
import assertk.assertions.isEqualTo
import ffeltrinelli.textualclock.domain.words.Word
import ffeltrinelli.textualclock.domain.words.english.Connector.IT_IS
import ffeltrinelli.textualclock.domain.words.english.Connector.O_CLOCK
import ffeltrinelli.textualclock.domain.words.english.Connector.PAST
import ffeltrinelli.textualclock.domain.words.english.Connector.TO
import ffeltrinelli.textualclock.domain.words.english.Hour.ELEVEN
import ffeltrinelli.textualclock.domain.words.english.Hour.FIVE
import ffeltrinelli.textualclock.domain.words.english.Hour.FOUR
import ffeltrinelli.textualclock.domain.words.english.Hour.NINE
import ffeltrinelli.textualclock.domain.words.english.Hour.ONE
import ffeltrinelli.textualclock.domain.words.english.Hour.SEVEN
import ffeltrinelli.textualclock.domain.words.english.Hour.SIX
import ffeltrinelli.textualclock.domain.words.english.Hour.TEN
import ffeltrinelli.textualclock.domain.words.english.Hour.THREE
import ffeltrinelli.textualclock.domain.words.english.Hour.TWELVE
import ffeltrinelli.textualclock.domain.words.english.Hour.TWO
import ffeltrinelli.textualclock.domain.words.english.Minutes
import ffeltrinelli.textualclock.domain.words.english.Minutes.HALF
import ffeltrinelli.textualclock.domain.words.english.Minutes.QUARTER
import ffeltrinelli.textualclock.domain.words.english.Minutes.TWENTY
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalTime

@RunWith(JUnitParamsRunner::class)
class EnglishTimeTest {

    companion object {
        const val HOUR = 10
        const val MINUTE = 20
        val TIME: LocalTime = LocalTime.of(HOUR, MINUTE)
    }

    private val underTest = EnglishTime.fixed(HOUR, MINUTE)

    @Test
    fun `returns current time from clock`() {
        val result = underTest.currentTime()
        assertThat(result).isEqualTo(TIME)
    }

    @Test
    @Parameters(method = "timeToWordsExamples")
    fun `converts time to words`(time: LocalTime, expected: List<Word>){
        val result = underTest.convertToWords(time)
        assertThat(result).isEqualTo(expected)
    }

    fun timeToWordsExamples() = arrayOf(
        arrayOf(time("00:00"), words(IT_IS, TWELVE, O_CLOCK)),
        arrayOf(time("01:00"), words(IT_IS, ONE, O_CLOCK)),
        arrayOf(time("01:04"), words(IT_IS, ONE, O_CLOCK)),
        arrayOf(time("02:05"), words(IT_IS, Minutes.FIVE, PAST, TWO)),
        arrayOf(time("02:09"), words(IT_IS, Minutes.FIVE, PAST, TWO)),
        arrayOf(time("03:10"), words(IT_IS, Minutes.TEN, PAST, THREE)),
        arrayOf(time("03:14"), words(IT_IS, Minutes.TEN, PAST, THREE)),
        arrayOf(time("16:15"), words(IT_IS, QUARTER, PAST, FOUR)),
        arrayOf(time("16:19"), words(IT_IS, QUARTER, PAST, FOUR)),
        arrayOf(time("17:20"), words(IT_IS, TWENTY, PAST, FIVE)),
        arrayOf(time("17:24"), words(IT_IS, TWENTY, PAST, FIVE)),
        arrayOf(time("18:25"), words(IT_IS, TWENTY, Minutes.FIVE, PAST, SIX)),
        arrayOf(time("18:29"), words(IT_IS, TWENTY, Minutes.FIVE, PAST, SIX)),
        arrayOf(time("19:30"), words(IT_IS, HALF, PAST, SEVEN)),
        arrayOf(time("19:34"), words(IT_IS, HALF, PAST, SEVEN)),
        arrayOf(time("20:35"), words(IT_IS, TWENTY, Minutes.FIVE, TO, NINE)),
        arrayOf(time("20:39"), words(IT_IS, TWENTY, Minutes.FIVE, TO, NINE)),
        arrayOf(time("21:40"), words(IT_IS, TWENTY, TO, TEN)),
        arrayOf(time("21:44"), words(IT_IS, TWENTY, TO, TEN)),
        arrayOf(time("22:45"), words(IT_IS, QUARTER, TO, ELEVEN)),
        arrayOf(time("22:49"), words(IT_IS, QUARTER, TO, ELEVEN)),
        arrayOf(time("23:50"), words(IT_IS, Minutes.TEN, TO, TWELVE)),
        arrayOf(time("23:54"), words(IT_IS, Minutes.TEN, TO, TWELVE)),
        arrayOf(time("23:55"), words(IT_IS, Minutes.FIVE, TO, TWELVE)),
        arrayOf(time("23:59"), words(IT_IS, Minutes.FIVE, TO, TWELVE)),
    )

    private fun time(text: String) = LocalTime.parse(text)
    private fun words(vararg words: Word) = words.toList()
}
