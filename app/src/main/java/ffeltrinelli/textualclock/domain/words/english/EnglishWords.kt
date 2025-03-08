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

@Suppress("MagicNumber")
enum class Hour(private val text: String, private val amPmHour: Int): Word {
    ONE("one", 1),
    TWO("two", 2),
    THREE("three", 3),
    FOUR("four", 4),
    FIVE("five", 5),
    SIX("six", 6),
    SEVEN("seven", 7),
    EIGHT("eight", 8),
    NINE("nine", 9),
    TEN("ten", 10),
    ELEVEN("eleven", 11),
    TWELVE("twelve", 12);

    override fun text() = text

    companion object {
        fun fromAmPmHour(amPmHour: Int): Hour = Hour.entries.first { it.amPmHour == amPmHour }
    }
}
