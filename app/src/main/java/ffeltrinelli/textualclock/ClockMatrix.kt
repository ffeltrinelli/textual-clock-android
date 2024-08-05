package ffeltrinelli.textualclock

class ClockMatrix() {
    private val data: MutableList<List<Symbol>> = mutableListOf()

    fun addRow(vararg rowSymbols: Symbol): ClockMatrix {
        data.add(rowSymbols.toList())
        return this
    }

    fun rows(): List<List<Symbol>> = data

    fun maxRowChars() : Int = data.maxOf { rowSymbols -> rowSymbols.sumOf { it.text().length } }

    companion object {
        val ENGLISH_CLOCK = ClockMatrix()
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
    }
}
