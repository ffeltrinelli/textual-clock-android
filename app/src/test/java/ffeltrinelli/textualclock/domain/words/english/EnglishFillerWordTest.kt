package ffeltrinelli.textualclock.domain.words.english

import ffeltrinelli.textualclock.domain.Randomizer
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class EnglishFillerWordTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var randomizer: Randomizer

    @Test
    fun `empty string`() {
        val underTest = EnglishFillerWord(length = 0, randomizer)

        val result = underTest.text()

        assertEquals("", result)
    }

    @Test
    fun `single-letter string`() {
        every { randomizer.englishLowercaseLetter() } returnsMany listOf('x')
        val underTest = EnglishFillerWord(length = 1, randomizer)

        val result = underTest.text()

        assertEquals("x", result)
    }

    @Test
    fun `multi-letters string`() {
        every { randomizer.englishLowercaseLetter() } returnsMany listOf('a', 'b')
        val underTest = EnglishFillerWord(length = 2, randomizer)

        val result = underTest.text()

        assertEquals("ab", result)
    }
}
