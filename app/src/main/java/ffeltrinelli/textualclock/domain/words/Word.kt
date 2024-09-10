package ffeltrinelli.textualclock.domain.words

interface Word {
    fun text(): String
    fun length(): Int = text().length

    /**
     * Converts this [Word] into an un-selected [SelectableWord]
     */
    fun toUnselected(): SelectableWord = SelectableWord(this, isSelected = false)
}
