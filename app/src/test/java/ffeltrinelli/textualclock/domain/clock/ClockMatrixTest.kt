package ffeltrinelli.textualclock.domain.clock

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import ffeltrinelli.textualclock.domain.words.english.Connector.IT_IS
import ffeltrinelli.textualclock.domain.words.english.Connector.PAST
import ffeltrinelli.textualclock.domain.words.english.Connector.TO
import org.junit.Test

class ClockMatrixTest {

    private lateinit var underTest: ClockMatrix

    @Test
    fun `throws if no rows`() {
        assertFailure {
            underTest = FakeClock(emptyList())
        }.isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    fun `throws if rows with different length`() {
        val rows = listOf(
            WordsRow(listOf(IT_IS)),
            WordsRow(listOf(PAST, TO))
        )
        assertFailure {
            underTest = FakeClock(rows)
        }.isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    fun `rowLength is the sum of characters in all rows`() {
        val rows = listOf(
            WordsRow(listOf(IT_IS)),
            WordsRow(listOf(PAST))
        )
        underTest = FakeClock(rows)
        assertThat(underTest).prop(ClockMatrix::rowLength).isEqualTo(4)
    }

    @Test
    fun `words is the flattened list of all words`() {
        val rows = listOf(
            WordsRow(listOf(TO, IT_IS)),
            WordsRow(listOf(PAST, TO))
        )
        underTest = FakeClock(rows)
        assertThat(underTest).prop(ClockMatrix::words).containsExactly(TO, IT_IS, PAST, TO)
    }

    class FakeClock(rows: List<WordsRow>) : ClockMatrix(rows)
}
