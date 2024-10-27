package ffeltrinelli.textualclock.domain.clock

import java.time.LocalTime

/**
 * Clock providing current local time.
 */
fun interface LocalClock {
    fun now(): LocalTime
}
