package ffeltrinelli.textualclock.domain.words

import ffeltrinelli.textualclock.domain.RandomGenerator
import ffeltrinelli.textualclock.domain.words.english.Filler
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
    lateinit var generator: RandomGenerator

    @Test
    fun `empty string`() {
        val underTest = Filler(length = 0, generator)

        val result = underTest.text()

        assertEquals("", result)
    }

    @Test
    fun `single-letter string`() {
        every { generator.nextLetter() } returnsMany listOf('x')
        val underTest = Filler(length = 1, generator)

        val result = underTest.text()

        assertEquals("x", result)
    }

    @Test
    fun `multi-letters string`() {
        every { generator.nextLetter() } returnsMany listOf('a', 'b')
        val underTest = Filler(length = 2, generator)

        val result = underTest.text()

        assertEquals("ab", result)
    }
}
