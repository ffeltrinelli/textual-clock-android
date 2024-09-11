package ffeltrinelli.textualclock.domain.words.english

import ffeltrinelli.textualclock.domain.Randomizer
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FillerTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var randomizer: Randomizer

    @Test
    fun `empty string`() {
        val underTest = Filler(length = 0, randomizer)

        val result = underTest.text()

        assertEquals("", result)
    }

    @Test
    fun `single-letter string`() {
        every { randomizer.nextLetter() } returnsMany listOf('x')
        val underTest = Filler(length = 1, randomizer)

        val result = underTest.text()

        assertEquals("x", result)
    }

    @Test
    fun `multi-letters string`() {
        every { randomizer.nextLetter() } returnsMany listOf('a', 'b')
        val underTest = Filler(length = 2, randomizer)

        val result = underTest.text()

        assertEquals("ab", result)
    }
}
