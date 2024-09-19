package ffeltrinelli.textualclock.domain.clock.fill

import ffeltrinelli.textualclock.domain.clock.ClockRow

/**
 * Strategy to fill rows with filler characters.
 */
interface ClockRowFiller {
    /**
     * Build a new row out of an existing one adding
     * the given number of filler characters.
     *
     * @param row the row be filled
     * @param fillersNum the number of filler characters to be added
     */
    fun fillRow(row: ClockRow, fillersNum: Int): ClockRow
}
