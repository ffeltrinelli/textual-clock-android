package ffeltrinelli.textualclock.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class FixedPreferencesHelperTest {

    private lateinit var underTest: FixedPreferencesHelper

    @Test
    @Parameters(value = ["0", "5"])
    fun `returns the fixed wordsPerRow preference`(wordsPerRow: Int) {
        underTest = FixedPreferencesHelper(wordsPerRow)

        val result = underTest.getWordsPerRow()

        assertThat(result).isEqualTo(wordsPerRow)
    }
}
