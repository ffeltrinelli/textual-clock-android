package ffeltrinelli.textualclock.domain

import javax.inject.Inject
import kotlin.random.Random

class Randomizer @Inject constructor(
    private val random: Random
) {
    fun englishLowercaseLetter() = random.nextInt('a'.code, 'z'.code + 1).toChar()

    /**
     * Generates an integer in the given range, inclusive.
     */
    // TODO add unit test
    fun intBetween(from: Int, until: Int) = random.nextInt(from, until + 1)
}
