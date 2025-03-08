package ffeltrinelli.textualclock

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import ffeltrinelli.textualclock.data.FixedPreferencesHelper
import ffeltrinelli.textualclock.domain.clock.english.EnglishTime
import ffeltrinelli.textualclock.domain.clock.fill.FixedRowFiller
import ffeltrinelli.textualclock.model.ClockViewModel
import ffeltrinelli.textualclock.ui.composable.MainComposable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        keepScreenOn()
        setContent {
            MainComposable()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemBars()
    }

    private fun keepScreenOn() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun hideSystemBars() {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("MagicNumber", "UnusedPrivateMember")
private fun TextualClockPreview() {
    val rowFiller = FixedRowFiller('a')
    val englishTime = EnglishTime.fixed(3, 25)
    val preferencesHelper = FixedPreferencesHelper(wordsPerRow = 2)
    MainComposable(clockViewModel = ClockViewModel(rowFiller, englishTime, preferencesHelper))
}
