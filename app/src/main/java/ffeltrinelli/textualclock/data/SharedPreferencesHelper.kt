package ffeltrinelli.textualclock.data

import android.content.Context
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.preference.PreferenceManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SharedPreferencesHelper @Inject constructor(
    @ApplicationContext context: Context
): PreferencesHelper {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getWordsPerRow(): Int {
        val wordsPerRow = preferences.getFloat(WORDS_PER_ROW_KEY, WORDS_PER_ROW_DEFAULT.toFloat()).toInt()
        Log.d(TAG, "wordsPerRow: $wordsPerRow")
        return wordsPerRow
    }

    override fun listenToPreferenceChange(listener: OnSharedPreferenceChangeListener) {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    companion object {
        val TAG = SharedPreferencesHelper::class.simpleName
        const val WORDS_PER_ROW_KEY = "words_per_row"
        const val WORDS_PER_ROW_DEFAULT = 1
    }
}
