package ffeltrinelli.textualclock.domain

import javax.inject.Inject
import kotlin.random.Random

/**
 * Random generation of values.
 */
class Randomizer @Inject constructor(
    private val random: Random
) {
    /**
     * Generates a random letter between 'a' and 'z', lowercase.
     */
    fun englishLowercaseLetter() = random.nextInt('a'.code, 'z'.code + 1).toChar()

    /**
     * Generates a random integer in the given range, inclusive.
     *
     * @throws IllegalArgumentException if ```from``` greater than ```until```
     */
    fun intBetween(from: Int, until: Int) = random.nextInt(from, until + 1)
}
