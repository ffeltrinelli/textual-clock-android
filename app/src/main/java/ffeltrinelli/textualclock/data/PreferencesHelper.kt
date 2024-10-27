package ffeltrinelli.textualclock.data

/**
 * Helper to retrieve application preferences and
 * be notified of changes.
 */
interface PreferencesHelper {
    fun getWordsPerRow(): Int
    fun listenToPreferenceChange(listener: PreferenceChangeListener)
}

/**
 * A listener that will be notified of preference changes.
 */
fun interface PreferenceChangeListener {
    fun onPreferenceChanged(key: String)
}
