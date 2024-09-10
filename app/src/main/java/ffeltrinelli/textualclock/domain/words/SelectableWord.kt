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
}
