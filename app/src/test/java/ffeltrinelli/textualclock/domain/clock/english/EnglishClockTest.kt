package ffeltrinelli.textualclock.domain.clock.english

import assertk.Assert
import assertk.assertThat
import assertk.assertions.each
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotEmpty
import assertk.assertions.matchesPredicate
import assertk.assertions.prop
import assertk.assertions.support.expected
import assertk.assertions.support.show
import ffeltrinelli.textualclock.domain.Randomizer
import ffeltrinelli.textualclock.domain.clock.ClockMatrix
import ffeltrinelli.textualclock.domain.clock.ClockRow
import ffeltrinelli.textualclock.domain.words.SelectableWord
import ffeltrinelli.textualclock.domain.words.Word
import ffeltrinelli.textualclock.domain.words.english.Connector
import ffeltrinelli.textualclock.domain.words.english.Connector.IT_IS
import ffeltrinelli.textualclock.domain.words.english.Filler
import ffeltrinelli.textualclock.domain.words.english.Hour
import ffeltrinelli.textualclock.domain.words.english.Minutes
import ffeltrinelli.textualclock.domain.words.english.Minutes.FIVE
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalTime

private const val RANDOM_LETTER = 'y'

class EnglishClockTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var randomizer: Randomizer

    @MockK
    private lateinit var englishTime: EnglishTime

    private lateinit var underTest: EnglishClock

    companion object {
        private val ENGLISH_WORDS: List<Word> = Connector.entries + Minutes.entries + Hour.entries
        private val CURRENT_TIME = LocalTime.parse("12:10")
        private val CURRENT_TIME_WORDS: List<Word> = listOf(IT_IS, FIVE)
    }

    @Before
    fun init() {
        every { randomizer.nextLetter() } returns RANDOM_LETTER
        every { englishTime.currentTime() } returns CURRENT_TIME
        every { englishTime.convertToWords(CURRENT_TIME) } returns CURRENT_TIME_WORDS
        underTest = EnglishClock(randomizer, englishTime)
    }

    @Test
    fun `contains at least one row`() {
        assertThat(underTest.rows).isNotEmpty()
    }

    @Test
    fun `all rows have same length`() {
        assertThat(underTest.rows).each { it.prop(ClockRow::length).isEqualTo(underTest.rowLength) }
    }

    @Test
    fun `contains each english word only once`() {
        ENGLISH_WORDS.forEach { word ->
            assertThat(underTest).containsOnlyOnce(word)
        }
    }

    @Test
    fun `other than english words, all others are fillers`() {
        val nonEnglishWords = underTest.allWords().map { it.value }.minus(ENGLISH_WORDS)
        assertThat(nonEnglishWords).each {
            it.isInstanceOf(Filler::class).prop(Word::text).isRepetitionOfCharacter(RANDOM_LETTER)
        }
    }

    @Test
    fun `all current time words are selected, the others are not`() {
        assertThat(underTest.allWords()).each { it.matchesPredicate { word ->
            word.isSelected == word.value in CURRENT_TIME_WORDS
        } }
    }
}

private fun Assert<ClockMatrix>.containsOnlyOnce(word: Word) = given { actual ->
    val wordOccurrences = actual.rows.sumOf { row -> row.words.filter { it.value == word }.size }
    if (wordOccurrences == 1) return
    expected("only one occurrence of ${show(word)} but found $wordOccurrences: ${show(actual.rows)}")
}

private fun Assert<String>.isRepetitionOfCharacter(char: Char) = given { actual ->
    if (actual.isNotEmpty() && actual.all { it == char }) return
    expected("a repetition of character ${show(char)} but found: ${show(actual)}")
}

private fun ClockMatrix.allWords(): List<SelectableWord> = rows.flatMap { row -> row.words }
