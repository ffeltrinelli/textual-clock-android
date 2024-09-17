package ffeltrinelli.textualclock.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ffeltrinelli.textualclock.domain.Randomizer
import ffeltrinelli.textualclock.domain.clock.TextualClock
import ffeltrinelli.textualclock.domain.clock.english.EnglishClock
import ffeltrinelli.textualclock.domain.clock.english.EnglishTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClockViewModel @Inject constructor(
    private val randomizer: Randomizer,
    private val englishTime: EnglishTime
): ViewModel() {
    private val clockState = mutableStateOf(EnglishClock(randomizer, englishTime, WORDS_PER_ROW))

    fun state(): State<TextualClock> = clockState

    init {
        startClockTick()
    }

    private fun startClockTick() {
        viewModelScope.launch {
            while (coroutineContext.isActive) {
                println("Updating clock")
                clockState.value = clockState.value.updateWordsSelection()
                delay(ONE_MINUTE)
            }
        }
    }

    companion object {
        private const val ONE_MINUTE = 1000L * 60

        // TODO add app configuration for this
        private const val WORDS_PER_ROW = 2
    }
}
