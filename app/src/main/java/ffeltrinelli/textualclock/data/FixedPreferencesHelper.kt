package ffeltrinelli.textualclock.data

/**
 * [PreferencesHelper] that always returns the given static preference values.
 * To be used in unit tests and Compose Preview.
 */
class FixedPreferencesHelper(private val wordsPerRow: Int): PreferencesHelper {
    override fun getWordsPerRow() = wordsPerRow

    override fun listenToPreferenceChange(listener: PreferenceChangeListener) { /* do nothing */ }
}
