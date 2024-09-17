package ffeltrinelli.textualclock.domain.clock

/**
 * Configurations to build the clock.
 *
 * @param wordsPerRoW how many words of the selected language will be put in each clock row.
 * The last row may contain less than this number.
 */
data class ClockConfig(val wordsPerRoW: Int)
