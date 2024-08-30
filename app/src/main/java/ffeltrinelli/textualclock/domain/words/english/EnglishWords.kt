package ffeltrinelli.textualclock.domain.words.english

import ffeltrinelli.textualclock.domain.words.Word

enum class Connector(private val text: String): Word {
    IT_IS("it's"),
    PAST("past"),
    TO("to"),
    O_CLOCK("o'clock");

    override fun text() = text
}

enum class Minutes(private val text: String): Word {
    FIVE("five"),
    TEN("ten"),
    QUARTER("quarter"),
    TWENTY("twenty"),
    HALF("half");

    override fun text() = text
}

enum class Hour(private val text: String): Word {
    ONE("one"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    NINE("nine"),
    TEN("ten"),
    ELEVEN("eleven"),
    TWELVE("twelve");

    override fun text() = text
}

/**
 * List with all English words.
 */
val ENGLISH_WORDS: List<Word> = Connector.entries + Minutes.entries + Hour.entries
