package ffeltrinelli.textualclock.data

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ffeltrinelli.textualclock.Application
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferencesHelperTest {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var underTest: SharedPreferencesHelper

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        underTest = SharedPreferencesHelper(context)
    }

    @Test
    fun `should retrieve default wordsPerRow if not set in SharedPreferences`() {
        val actual = underTest.getWordsPerRow()

        assertEquals(SharedPreferencesHelper.WORDS_PER_ROW_DEFAULT, actual)
    }

    @Test
    fun `should retrieve wordsPerRow from SharedPreferences`() {
        sharedPreferences.edit().putFloat(SharedPreferencesHelper.WORDS_PER_ROW_KEY, 3F).commit()

        val actual = underTest.getWordsPerRow()

        assertEquals(3, actual)
    }

    @Test
    fun `should notify listener of wordsPerRow changes just once`() {
        var notifiedTimes = 0
        var notifiedKey: String? = null
        underTest.listenToPreferenceChange {
            notifiedTimes++
            notifiedKey = it
        }

        sharedPreferences.edit().putFloat(SharedPreferencesHelper.WORDS_PER_ROW_KEY, 4F).commit()

        assertEquals(1, notifiedTimes)
        assertEquals(SharedPreferencesHelper.WORDS_PER_ROW_KEY, notifiedKey)
    }
}
