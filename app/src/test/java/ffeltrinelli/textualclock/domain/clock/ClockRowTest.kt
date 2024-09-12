package ffeltrinelli.textualclock.domain.clock

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import ffeltrinelli.textualclock.domain.clock.ClockRow.Companion.row
import ffeltrinelli.textualclock.domain.words.SelectableWord
import ffeltrinelli.textualclock.domain.words.english.Connector.IT_IS
import ffeltrinelli.textualclock.domain.words.english.Connector.O_CLOCK
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

    @Test
    fun `selects words in the list, deselects others, ignores missing ones`() {
        underTest = ClockRow(listOf(
            SelectableWord(IT_IS, isSelected = false),
            SelectableWord(PAST, isSelected = true)
        ))

        val result = underTest.selectWordsIn(listOf(IT_IS, O_CLOCK))

        assertThat(result).prop(ClockRow::words).containsExactly(
            SelectableWord(IT_IS, isSelected = true),
            SelectableWord(PAST, isSelected = false)
        )
    }
}
