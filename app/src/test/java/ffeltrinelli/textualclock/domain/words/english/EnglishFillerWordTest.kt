package ffeltrinelli.textualclock.domain.words.english

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.hashCodeFun
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotEqualTo
import ffeltrinelli.textualclock.domain.Randomizer
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test

class EnglishFillerWordTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var randomizer: Randomizer

    private lateinit var underTest: EnglishFillerWord

    @Test
    fun `throws if length is negative`() {
        assertFailure {
            underTest = EnglishFillerWord(length = -1, randomizer)
        }.isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    fun `throws if length is zero`() {
        assertFailure {
            underTest = EnglishFillerWord(length = 0, randomizer)
        }.isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    fun `single-letter string`() {
        every { randomizer.englishLowercaseLetter() } returnsMany listOf('x')
        underTest = EnglishFillerWord(length = 1, randomizer)

        val result = underTest.text()

        assertThat(result).isEqualTo("x")
    }

    @Test
    fun `multi-letters string`() {
        every { randomizer.englishLowercaseLetter() } returnsMany listOf('a', 'b')
        underTest = EnglishFillerWord(length = 2, randomizer)

        val result = underTest.text()

        assertThat(result).isEqualTo("ab")
    }

    @Test
    fun `test equals and hashcode`() {
        every { randomizer.englishLowercaseLetter() } returnsMany listOf('a', 'a', 'b')
        underTest = EnglishFillerWord(length = 1, randomizer)
        val otherEqual = EnglishFillerWord(length = 1, randomizer)
        val otherDifferent = EnglishFillerWord(length = 1, randomizer)

        assertThat(underTest).isEqualTo(otherEqual)
        assertThat(underTest).hashCodeFun().isEqualTo(otherEqual.hashCode())
        assertThat(underTest).isNotEqualTo(otherDifferent)
        assertThat(underTest).hashCodeFun().isNotEqualTo(otherDifferent.hashCode())
    }
}
