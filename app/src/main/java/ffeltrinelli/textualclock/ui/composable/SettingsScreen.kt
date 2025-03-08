package ffeltrinelli.textualclock.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
            intSliderPreference(
                key = SharedPreferencesHelper.WORDS_PER_ROW_KEY,
                minValue = 1,
                maxValue = 5,
                title = "Words per row",
                summary = "How many clock words to show per row",
            )
        }
    }
}

private fun LazyListScope.intSliderPreference(
    key: String,
    minValue: Int,
    maxValue: Int,
    title: String,
    summary: String
) {
    sliderPreference(
        key = key,
        defaultValue = minValue.toFloat(),
        title = { Text(title) },
        summary = { Text(summary) },
        valueRange = minValue.toFloat()..maxValue.toFloat(),
        valueSteps = maxValue - 2,
        valueText = { Text(text = "${it.toInt()}") },
    )
}
