package ffeltrinelli.textualclock.data

import android.content.Context
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
        Log.i("MyPreferences", wordsPerRow.toString())
        return wordsPerRow
    }

    companion object {
        const val WORDS_PER_ROW_KEY = "words_per_row"
        const val WORDS_PER_ROW_DEFAULT = 1
    }
}
