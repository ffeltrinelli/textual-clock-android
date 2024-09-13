package ffeltrinelli.textualclock.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ffeltrinelli.textualclock.domain.Randomizer
import ffeltrinelli.textualclock.domain.clock.ClockMatrix
import ffeltrinelli.textualclock.domain.clock.english.EnglishClock
import ffeltrinelli.textualclock.domain.clock.english.EnglishTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Clock
import kotlin.random.Random

class ClockViewModel: ViewModel() {
    private val clockState = mutableStateOf(rebuildClock())

    fun state(): State<ClockMatrix> = clockState

    init {
        startClockTick()
    }

    private fun startClockTick() {
        viewModelScope.launch {
            while (coroutineContext.isActive) {
                println("Updating clock")
                clockState.value = rebuildClock()
                delay(ONE_MINUTE)
            }
        }
    }

    companion object {
        private const val ONE_MINUTE = 1000L * 60

        // TODO add Dependency Injection
        private val RANDOMIZER = Randomizer(Random)
        private val ENGLISH_TIME = EnglishTime(Clock.systemDefaultZone())

        private fun rebuildClock(): ClockMatrix = EnglishClock(RANDOMIZER, ENGLISH_TIME)
    }
}
