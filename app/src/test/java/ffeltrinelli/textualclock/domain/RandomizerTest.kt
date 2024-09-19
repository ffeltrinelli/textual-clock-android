package ffeltrinelli.textualclock.domain

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

class RandomizerTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var random: Random

    lateinit var underTest: Randomizer

    @Before
    fun init() {
        underTest = Randomizer(random)
    }

    @Test
    fun `englishLowercaseLetter should generate a lower case letter from 'a' to 'z'`() {
        every { random.nextInt(any(), any()) } returns 'd'.code

        val result = underTest.englishLowercaseLetter()

        assertEquals('d', result)
        verify { random.nextInt('a'.code, 'z'.code + 1) }
    }
}
