package ffeltrinelli.textualclock.domain.words

interface Word {
    fun text(): String
    fun length(): Int = text().length
}
