package ffeltrinelli.textualclock.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ffeltrinelli.textualclock.domain.clock.TextualClock
import ffeltrinelli.textualclock.ui.theme.BlueLight

@Composable
fun ClockScreen(textualClock: TextualClock, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().background(BlueLight),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(textualClock.rowLength)
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
