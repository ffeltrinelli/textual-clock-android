package ffeltrinelli.textualclock.domain.clock

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import ffeltrinelli.textualclock.domain.clock.ClockRow.Companion.unselectedRowFrom
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
            unselectedRowFrom(listOf(IT_IS)),
            unselectedRowFrom(listOf(PAST, TO))
        )
        assertFailure {
            underTest = FakeClock(rows)
        }.isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    fun `rowLength is the sum of characters in all rows`() {
        val rows = listOf(
            unselectedRowFrom(listOf(IT_IS)),
            unselectedRowFrom(listOf(PAST))
        )
        underTest = FakeClock(rows)
        assertThat(underTest).prop(ClockMatrix::rowLength).isEqualTo(4)
    }

    class FakeClock(rows: List<ClockRow>) : ClockMatrix(rows)
}
