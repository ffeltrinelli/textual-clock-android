package ffeltrinelli.textualclock.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ffeltrinelli.textualclock.data.SharedPreferencesHelper
import ffeltrinelli.textualclock.domain.clock.TextualClock
import ffeltrinelli.textualclock.model.ClockViewModel
import ffeltrinelli.textualclock.ui.theme.BlueLight
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.sliderPreference

@Composable
fun ClockGrid(modifier: Modifier = Modifier, clockViewModel: ClockViewModel = viewModel()) {
    val textualClock: TextualClock by clockViewModel.state()
    Box(
        modifier = Modifier.fillMaxSize().background(BlueLight),
        contentAlignment = Alignment.Center
    ) {
        ProvidePreferenceLocals {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(textualClock.rowLength),
            modifier = modifier
        ) {
            textualClock.rows.forEach { row ->
                row.words.forEach { word ->
                    word.text.forEach { char ->
                        item { CharCell(char.uppercaseChar(), isHighlighted = word.isSelected) }
                    }
                }
            }
        }
    }
}
