package ffeltrinelli.textualclock.domain.clock.english

import assertk.Assert
import assertk.assertThat
import assertk.assertions.each
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotEmpty
import assertk.assertions.prop
import assertk.assertions.support.expected
import assertk.assertions.support.show
import ffeltrinelli.textualclock.domain.RandomGenerator
import ffeltrinelli.textualclock.domain.clock.ClockMatrix
import ffeltrinelli.textualclock.domain.clock.WordsRow
import ffeltrinelli.textualclock.domain.words.Word
import ffeltrinelli.textualclock.domain.words.english.ENGLISH_WORDS
import ffeltrinelli.textualclock.domain.words.english.Filler
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val RANDOM_LETTER = 'y'

class EnglishClockTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var generator: RandomGenerator

    private lateinit var underTest: EnglishClock

    @Before
    fun init() {
        every { generator.nextLetter() } returns RANDOM_LETTER
        underTest = EnglishClock(generator)
    }

    @Test
    fun `contains at least one row`() {
        assertThat(underTest.rows).isNotEmpty()
    }

    @Test
    fun `all rows have same length`() {
        assertThat(underTest.rows).each { it.prop(WordsRow::length).isEqualTo(underTest.rowLength) }
    }

    @Test
    fun `contains each english word only once`() {
        ENGLISH_WORDS.forEach { word ->
            assertThat(underTest).containsOnlyOnce(word)
        }
    }

    @Test
    fun `other than english words, all others are fillers`() {
        val nonEnglishWords = underTest.words.minus(ENGLISH_WORDS)
        assertThat(nonEnglishWords).each {
            it.isInstanceOf(Filler::class).prop(Word::text).isRepetitionOfCharacter(RANDOM_LETTER)
        }
    }
}

private fun Assert<ClockMatrix>.containsOnlyOnce(word: Word) = given { actual ->
    val wordOccurrences = actual.rows.sumOf { row -> row.words.filter { it == word }.size }
    if (wordOccurrences == 1) return
    expected("only one occurrence of ${show(word)} but found $wordOccurrences: ${show(actual.rows)}")
}

private fun Assert<String>.isRepetitionOfCharacter(char: Char) = given { actual ->
    if (actual.isNotEmpty() && actual.all { it == char }) return
    expected("a repetition of character ${show(char)} but found: ${show(actual)}")
}
