package ffeltrinelli.textualclock.domain.clock.fill

import ffeltrinelli.textualclock.domain.Randomizer
import ffeltrinelli.textualclock.domain.clock.ClockRow
import ffeltrinelli.textualclock.domain.words.SelectableWord
import ffeltrinelli.textualclock.domain.words.english.EnglishFillerWord
import javax.inject.Inject

/**
 * Filling strategy where filler characters are randomly distributed between words.
 */
class RandomRowFiller @Inject constructor(
    private val randomizer: Randomizer
): ClockRowFiller {

    override fun fillRow(
        row: ClockRow,
        fillersNum: Int
    ): ClockRow {
        require(fillersNum >= 0) {"fillersNum cannot be negative"}
        if (fillersNum == 0) return row

        val fillersPerSlot: List<Int> = decideFillersPerSlot(row, fillersNum)
        val mergedWords: List<SelectableWord> = mergeFillersWithRow(row, fillersPerSlot)
        return ClockRow(mergedWords)
    }

    /**
     * Decide how many filler characters should be added to each slot
     * between the words of the original row.
     *
     * @return a list of numbers such that ```list[i]``` = how many filler characters
     * to put in the original row after word with index ```i```
     */
    private fun decideFillersPerSlot(row: ClockRow, fillersNum: Int): List<Int> {
        // init all slot with zeros
        val fillersPerSlot = MutableList(row.words.size + 1) { 0 }
        var remainingFillers = fillersNum

        // randomly assign a number of filler characters for each slot
        for (i in fillersPerSlot.indices) {
            val currentSlotFillersNum = randomizer.intBetween(0, remainingFillers)
            fillersPerSlot[i] = currentSlotFillersNum
            remainingFillers -= currentSlotFillersNum
            if (remainingFillers == 0) break
        }
        // if there are still filler characters that were not assigned to any slot,
        // assign them to last slot
        if (remainingFillers > 0) fillersPerSlot[fillersPerSlot.lastIndex] += remainingFillers

        return fillersPerSlot
    }

    private fun mergeFillersWithRow(
        row: ClockRow,
        fillersPerSlot: List<Int>
    ): List<SelectableWord> {
        val mergedWords = mutableListOf<SelectableWord>()
        for (i in fillersPerSlot.indices) {
            if (fillersPerSlot[i] > 0) {
                val filler = EnglishFillerWord(fillersPerSlot[i], randomizer)
                mergedWords.add(filler.toUnselected())
            }
            if (i <= row.words.lastIndex) {
                mergedWords.add(row.words[i])
            }
        }
        return mergedWords
    }
}
