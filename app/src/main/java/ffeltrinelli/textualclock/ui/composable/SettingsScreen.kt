package ffeltrinelli.textualclock.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ffeltrinelli.textualclock.data.SharedPreferencesHelper
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.sliderPreference

@Composable
fun SettingsScreen(modifier: Modifier) {
    ProvidePreferenceLocals {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            sliderPreference(
                key = SharedPreferencesHelper.WORDS_PER_ROW_KEY,
                defaultValue = 1f,
                title = { Text(text = "Words per row") },
                valueRange = 1f..5f,
                valueSteps = 3,
                valueText = { Text(text = "${it.toInt()}") },
            )
        }
    }
}
