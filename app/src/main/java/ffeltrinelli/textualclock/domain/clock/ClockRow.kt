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
