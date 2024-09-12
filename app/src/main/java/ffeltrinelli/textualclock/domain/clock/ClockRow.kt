package ffeltrinelli.textualclock.domain.clock

import ffeltrinelli.textualclock.domain.words.SelectableWord
import ffeltrinelli.textualclock.domain.words.Word

/**
 * Words in a row.
 */
data class ClockRow(val words: List<SelectableWord>) {

    init {
        require(words.isNotEmpty()) { "row cannot have zero words" }
    }

    /**
     * Total number of characters in this row.
     */
    val length = words.sumOf { it.length }

    /**
     * Builds a new row that is a copy of this one, in which
     * the row's words that are contained in the given selection list
     * are selected and the others aren't. Words that are not in the row
     * are ignored.
     */
    fun selectWordsIn(wordsToSelect: List<Word>): ClockRow = ClockRow(
        words.map { word -> word.updateSelection(word.value in wordsToSelect) }
    )

    companion object {
        /**
         * Creates a row with the given words.
         * All the words are un-selected.
         */
        fun row(vararg words: Word) = ClockRow(words.map { it.toUnselected() })
    }
}
