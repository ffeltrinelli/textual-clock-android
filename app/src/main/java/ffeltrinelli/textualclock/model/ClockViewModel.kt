package ffeltrinelli.textualclock.model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ffeltrinelli.textualclock.data.PreferenceChangeListener
import ffeltrinelli.textualclock.data.PreferencesHelper
import ffeltrinelli.textualclock.domain.clock.ClockConfig
import ffeltrinelli.textualclock.domain.clock.TextualClock
import ffeltrinelli.textualclock.domain.clock.english.EnglishClock
import ffeltrinelli.textualclock.domain.clock.english.EnglishTime
import ffeltrinelli.textualclock.domain.clock.fill.ClockRowFiller
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClockViewModel @Inject constructor(
    private val rowFiller: ClockRowFiller,
    private val englishTime: EnglishTime,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    private val clockState = mutableStateOf(clockFullRebuild())
    private val preferenceChangeListener =
        PreferenceChangeListener { key ->
            Log.i(TAG, "Preference $key changed, rebuilding clock")
            clockState.value = clockFullRebuild()
        }

    fun state(): State<TextualClock> = clockState

    init {
        startClockTick()
        preferencesHelper.listenToPreferenceChange(preferenceChangeListener)
    }

    private fun startClockTick() {
        viewModelScope.launch {
            while (coroutineContext.isActive) {
                Log.i(TAG, "Clock ticked, updating words selection")
                clockState.value = clockUpdateWordsSelection()
                delay(ONE_MINUTE)
            }
        }
    }

    private fun clockFullRebuild(): EnglishClock =
        EnglishClock(rowFiller, englishTime, ClockConfig(preferencesHelper.getWordsPerRow()))
    private fun clockUpdateWordsSelection(): EnglishClock = clockState.value.updateWordsSelection()

    companion object {
        val TAG = ClockViewModel::class.simpleName
        private const val ONE_MINUTE = 1000L * 60
    }
}
