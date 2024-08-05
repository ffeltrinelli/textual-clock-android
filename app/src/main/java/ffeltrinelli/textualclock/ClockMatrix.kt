package ffeltrinelli.textualclock

data class ClockMatrix(
    private val data: List<List<Word>>
) {
    class Builder {
        private val data: MutableList<List<Word>> = mutableListOf()

        fun addRow(vararg rowWords: Word) = apply { data.add(rowWords.toList()) }

        fun build(): ClockMatrix = ClockMatrix(data.toList())
    }

    fun rows(): List<List<Word>> = data

    fun maxRowChars() : Int = data.maxOf { rowSymbols -> rowSymbols.sumOf { it.text().length } }

    companion object {
        val ENGLISH_CLOCK = Builder()
            .addRow(Connector.IT_IS, Filler(7))
            .addRow(Minutes.QUARTER, Filler(4))
            .addRow(Minutes.TWENTY, Minutes.FIVE, Filler(1))
            .addRow(Minutes.HALF, Filler(1), Minutes.TEN, Filler(1), Connector.TO)
            .addRow(Connector.PAST, Filler(3), Hour.NINE)
            .addRow(Hour.ONE, Hour.SIX, Hour.THREE)
            .addRow(Hour.FOUR, Hour.FIVE, Hour.TWO)
            .addRow(Hour.EIGHT, Hour.ELEVEN)
            .addRow(Hour.SEVEN, Hour.TWELVE)
            .addRow(Hour.TEN, Filler(1), Connector.O_CLOCK)
            .build()
    }
}
