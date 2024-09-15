package ffeltrinelli.textualclock.domain

import javax.inject.Inject
import kotlin.random.Random

class Randomizer @Inject constructor(
    private val random: Random
) {
    fun nextLetter() = random.nextInt('a'.code, 'z'.code + 1).toChar()
}
