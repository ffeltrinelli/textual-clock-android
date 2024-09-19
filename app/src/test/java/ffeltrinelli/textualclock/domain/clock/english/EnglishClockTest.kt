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
import ffeltrinelli.textualclock.domain.clock.ClockConfig
import ffeltrinelli.textualclock.domain.clock.TextualClock
import ffeltrinelli.textualclock.domain.clock.ClockRow
import ffeltrinelli.textualclock.domain.clock.english.EnglishClock.Companion.ENGLISH_WORDS_ORDERED
import ffeltrinelli.textualclock.domain.clock.fill.ClockRowFiller
import ffeltrinelli.textualclock.domain.words.FillerWord
import ffeltrinelli.textualclock.domain.words.SelectableWord
import ffeltrinelli.textualclock.domain.words.Word
import ffeltrinelli.textualclock.domain.words.english.Connector.IT_IS
import ffeltrinelli.textualclock.domain.words.english.Minutes.FIVE
import ffeltrinelli.textualclock.domain.words.english.Minutes.TEN
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalTime

class EnglishClockTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    private val rowFiller: ClockRowFiller = FakeRowFiller()

    @MockK
    private lateinit var englishTime: EnglishTime

    private lateinit var underTest: EnglishClock

    companion object {
        private val CURRENT_TIME = LocalTime.parse("12:10")
        private val CURRENT_TIME_WORDS: List<Word> = listOf(IT_IS, FIVE)
        private val CURRENT_TIME_WORDS_2: List<Word> = listOf(TEN)
        private const val WORDS_PER_ROW = 2
    }

    @Before
    fun init() {
        every { englishTime.currentTime() } returns CURRENT_TIME
        every { englishTime.convertToWords(CURRENT_TIME) } returns CURRENT_TIME_WORDS
        underTest = EnglishClock(rowFiller, englishTime, ClockConfig(WORDS_PER_ROW))
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
        ENGLISH_WORDS_ORDERED.forEach { word ->
            assertThat(underTest).containsOnlyOnce(word)
        }
    }

    @Test
    fun `other than english words, all others are fillers`() {
        val nonEnglishWords = underTest.allWords().map { it.value }.minus(ENGLISH_WORDS_ORDERED)
        assertThat(nonEnglishWords).each {
            it.isInstanceOf(FillerWord::class).prop(Word::text).isRepetitionOfCharacter(FILLER_CHAR)
        }
    }

    @Test
    fun `all current time words are selected, the others are not`() {
        assertThat(underTest.allWords()).each { it.matchesPredicate { word ->
            word.isSelected == word.value in CURRENT_TIME_WORDS
        } }
    }

    @Test
    fun `when selection is updated words are the same but selection changes`() {
        val copyOfPreviousWords = underTest.allWords()
        every { englishTime.convertToWords(CURRENT_TIME) } returns CURRENT_TIME_WORDS_2

        val newWordsAfterSelectionUpdate = underTest.updateWordsSelection().allWords()

        assertThat(copyOfPreviousWords.map { it.value }).isEqualTo(newWordsAfterSelectionUpdate.map { it.value })
        assertThat(newWordsAfterSelectionUpdate).each { it.matchesPredicate { word ->
            word.isSelected == word.value in CURRENT_TIME_WORDS_2
        } }
    }
}

private fun Assert<TextualClock>.containsOnlyOnce(word: Word) = given { actual ->
    val wordOccurrences = actual.rows.sumOf { row -> row.words.filter { it.value == word }.size }
    if (wordOccurrences == 1) return
    expected("only one occurrence of ${show(word)} but found $wordOccurrences: ${show(actual.rows)}")
}

private fun Assert<String>.isRepetitionOfCharacter(char: Char) = given { actual ->
    if (actual.isNotEmpty() && actual.all { it == char }) return
    expected("a repetition of character ${show(char)} but found: ${show(actual)}")
}

private fun TextualClock.allWords(): List<SelectableWord> = rows.flatMap { row -> row.words }

private const val FILLER_CHAR = 'y'

private class FakeFillerWord(private val length: Int): FillerWord {
    override fun text(): String = FILLER_CHAR.toString().repeat(length)
}

private class FakeRowFiller: ClockRowFiller {
    override fun fillRow(row: ClockRow, fillersNum: Int) = ClockRow(
        row.words + FakeFillerWord(fillersNum).toUnselected()
    )
}
