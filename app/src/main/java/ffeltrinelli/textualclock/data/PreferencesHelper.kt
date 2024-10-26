package ffeltrinelli.textualclock.data

interface PreferencesHelper {
    fun getWordsPerRow(): Int
    fun listenToPreferenceChange(listener: PreferenceChangeListener)
}

interface PreferenceChangeListener {
    fun onPreferenceChanged(key: String)
}
