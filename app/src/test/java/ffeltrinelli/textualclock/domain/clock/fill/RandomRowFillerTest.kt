package ffeltrinelli.textualclock.domain.clock.fill

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import ffeltrinelli.textualclock.domain.Randomizer
import ffeltrinelli.textualclock.domain.clock.ClockRow
import ffeltrinelli.textualclock.domain.words.Word
import ffeltrinelli.textualclock.domain.words.english.Connector.IT_IS
import ffeltrinelli.textualclock.domain.words.english.Connector.TO
import ffeltrinelli.textualclock.domain.words.english.EnglishFillerWord
import ffeltrinelli.textualclock.domain.words.english.Minutes.TEN
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class RandomRowFillerTest {

    private val randomizer: Randomizer = mockk()
    private val underTest = RandomRowFiller(randomizer)

    companion object {
        val ROW = ClockRow(listOf(IT_IS.toUnselected()))
        const val FILLER_CHAR = 'w'
    }

    @Before
    fun init() {
        clearAllMocks()
        every { randomizer.englishLowercaseLetter() } returns FILLER_CHAR
    }

    @Test
    fun `throws if fillers number is negative`() {
        assertFailure {
            underTest.fillRow(ROW, fillersNum = -1)
        }.isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    fun `keeps same row if fillers number is zero`() {
        val result = underTest.fillRow(ROW, fillersNum = 0)
        assertThat(result).isEqualTo(ROW)
    }

    @Test
    @Parameters(method = "fillingRowSamples")
    fun `generates a filler of random length for each slot in the row from first slot to last slot`(
        startRow: ClockRow,
        fillersNum: Int,
        fillersPerSlot: List<Int>,
        expectedRow: ClockRow
    ) {
        every { randomizer.intBetween(any(), any()) } returnsMany fillersPerSlot

        val result = underTest.fillRow(startRow, fillersNum)

        assertThat(result).isEqualTo(expectedRow)
    }

    fun fillingRowSamples(): Array<Array<Any>> {
        every { randomizer.englishLowercaseLetter() } returns FILLER_CHAR
        return arrayOf(
            // all filler characters are randomly distributed:
            arrayOf(row(TEN), 1, fillersPerSlot(1, 0), row(filler(1), TEN)),
            arrayOf(row(TEN), 1, fillersPerSlot(0, 1), row(TEN, filler(1))),
            arrayOf(row(TEN), 2, fillersPerSlot(1, 1), row(filler(1), TEN, filler(1))),
            arrayOf(row(TEN), 2, fillersPerSlot(0, 2), row(TEN, filler(2))),
            arrayOf(row(TO, TEN), 3, fillersPerSlot(2, 0, 1), row(filler(2), TO, TEN, filler(1))),
            arrayOf(row(TO, TEN), 4, fillersPerSlot(2, 1, 1), row(filler(2), TO, filler(1), TEN, filler(1))),
            // some filler characters are still left after random distribution,
            // so it assigns the remaining ones to last slot:
            arrayOf(row(TEN), 1, fillersPerSlot(0, 0), row(TEN, filler(1))),
            arrayOf(row(TO, TEN), 3, fillersPerSlot(1, 0, 1), row(filler(1), TO, TEN, filler(2))),
            arrayOf(row(TO, TEN), 3, fillersPerSlot(0, 1, 0), row(TO, filler(1), TEN, filler(2))),
        )
    }

    private fun row(vararg words: Word) = ClockRow(words.map { it.toUnselected() })
    private fun fillersPerSlot(vararg numbers: Int) = numbers.toList()
    private fun filler(length: Int) = EnglishFillerWord(length, randomizer)
}
