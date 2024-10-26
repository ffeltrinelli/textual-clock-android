package ffeltrinelli.textualclock.data

import android.content.SharedPreferences.OnSharedPreferenceChangeListener

interface PreferencesHelper {
    fun getWordsPerRow(): Int
    fun listenToPreferenceChange(listener: OnSharedPreferenceChangeListener)
}
