package ffeltrinelli.textualclock.domain.words

/**
 * A word that can or cannot be in selected state.
 */
data class SelectableWord(
    val value: Word,
    val isSelected: Boolean = false
) {
    val text = value.text()
    val length = value.length()

    /**
     * Builds a copy of this word with the new selection status.
     */
    fun updateSelection(isSelected: Boolean) = SelectableWord(value, isSelected = isSelected)
}
