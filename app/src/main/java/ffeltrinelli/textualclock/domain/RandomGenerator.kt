package ffeltrinelli.textualclock.domain

import kotlin.random.Random

class RandomGenerator(private val random: Random) {
    fun nextLetter() = random.nextInt('a'.code, 'z'.code + 1).toChar()
}
