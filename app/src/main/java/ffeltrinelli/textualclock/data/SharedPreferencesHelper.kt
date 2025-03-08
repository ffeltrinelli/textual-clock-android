package ffeltrinelli.textualclock.data

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.preference.PreferenceManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Implementation of [PreferencesHelper] using the default [SharedPreferences].
 */
class SharedPreferencesHelper @Inject constructor(
    @ApplicationContext context: Context
) : PreferencesHelper {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    private val preferenceChangeListener = object : OnSharedPreferenceChangeListener {
        private val delegatedListeners = mutableListOf<PreferenceChangeListener>()
        fun addDelegatedListener(listener: PreferenceChangeListener) {
            delegatedListeners.add(listener)
        }
        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
            if (key != null && key in ALLOWED_KEYS) {
                delegatedListeners.forEach { it.onPreferenceChanged(key) }
            }
        }
    }

    init {
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun getWordsPerRow(): Int {
        val wordsPerRow = preferences.getFloat(WORDS_PER_ROW_KEY, WORDS_PER_ROW_DEFAULT.toFloat()).toInt()
        Log.d(TAG, "wordsPerRow: $wordsPerRow")
        return wordsPerRow
    }

    override fun listenToPreferenceChange(listener: PreferenceChangeListener) {
        preferenceChangeListener.addDelegatedListener(listener)
    }

    companion object {
        val TAG = SharedPreferencesHelper::class.simpleName
        const val WORDS_PER_ROW_KEY = "words_per_row"
        const val WORDS_PER_ROW_DEFAULT = 1
        val ALLOWED_KEYS = listOf(WORDS_PER_ROW_KEY)
    }
}
