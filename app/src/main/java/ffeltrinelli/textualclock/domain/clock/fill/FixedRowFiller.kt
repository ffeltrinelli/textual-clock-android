package ffeltrinelli.textualclock.domain.clock.fill

import ffeltrinelli.textualclock.domain.clock.ClockRow
import ffeltrinelli.textualclock.domain.words.FillerWord

/**
 * Simple [ClockRowFiller] that always fills at the beginning of the rows
 * by simply repeating the given filler character.
 * To be used in unit tests and Compose Preview.
 */
class FixedRowFiller(private val fillerChar: Char): ClockRowFiller {
    override fun fillRow(row: ClockRow, fillersNum: Int) = ClockRow(
        row.words + FixedFillerWord(fillersNum, fillerChar).toUnselected()
    )
}

private class FixedFillerWord(private val length: Int, private val fillerChar: Char): FillerWord {
    override fun text(): String = fillerChar.toString().repeat(length)
}
