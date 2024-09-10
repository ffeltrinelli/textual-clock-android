package ffeltrinelli.textualclock.domain.clock

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import ffeltrinelli.textualclock.domain.clock.ClockRow.Companion.row
import ffeltrinelli.textualclock.domain.words.english.Connector.IT_IS
import ffeltrinelli.textualclock.domain.words.english.Connector.PAST
import org.junit.Test

class ClockRowTest {

    private lateinit var underTest: ClockRow

    @Test
    fun `throws if no words`() {
        assertFailure {
            underTest = row()
        }.isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    fun `length is the total number of characters in the row`() {
        underTest = row(IT_IS, PAST)
        assertThat(underTest).prop(ClockRow::length).isEqualTo(8)
    }
}
