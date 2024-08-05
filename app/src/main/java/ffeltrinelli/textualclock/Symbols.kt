package ffeltrinelli.textualclock

interface Symbol {
    fun text(): String
}

enum class Connector(private val text: String): Symbol {
    IT_IS("it's"),
    PAST("past"),
    TO("to"),
    O_CLOCK("o'clock");

    override fun text() = text
}

enum class Minutes(private val text: String): Symbol {
    FIVE("five"),
    TEN("ten"),
    QUARTER("quarter"),
    TWENTY("twenty"),
    HALF("half");

    override fun text() = text
}

enum class Hour(private val text: String): Symbol {
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

class Filler(length: Int): Symbol {
    private val text = "-".repeat(length)

    override fun text() = text

}