package ffeltrinelli.textualclock.domain.words

import assertk.assertThat
import assertk.assertions.isEqualTo
import ffeltrinelli.textualclock.domain.words.english.Connector.O_CLOCK
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class SelectableWordTest {

    private lateinit var underTest: SelectableWord

    @Test
    @Parameters(value = ["true", "false"])
    fun `updates the selection status`(isSelected: Boolean) {
        underTest = SelectableWord(O_CLOCK, !isSelected)

        val result = underTest.updateSelection(isSelected)

        assertThat(result).isEqualTo(SelectableWord(O_CLOCK, isSelected))
    }
}
