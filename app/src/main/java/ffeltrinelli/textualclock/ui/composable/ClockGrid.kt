package ffeltrinelli.textualclock.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ffeltrinelli.textualclock.domain.clock.TextualClock
import ffeltrinelli.textualclock.model.ClockViewModel

@Composable
fun ClockGrid(modifier: Modifier = Modifier, clockViewModel: ClockViewModel = viewModel()) {
    val textualClock: TextualClock by clockViewModel.state()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(textualClock.rowLength),
            modifier = modifier
        ) {
            textualClock.rows.forEach { row ->
                row.words.forEach { word ->
                    word.text.forEach { char ->
                        item { CharCell(char, isHighlighted = word.isSelected) }
                    }
                }
            }
        }
    }
}
