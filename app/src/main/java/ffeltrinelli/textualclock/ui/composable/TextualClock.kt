package ffeltrinelli.textualclock.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ffeltrinelli.textualclock.domain.clock.ClockMatrix

@Composable
fun TextualClock(clockMatrix: ClockMatrix, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(clockMatrix.rowsLength),
            modifier = modifier
        ) {
            clockMatrix.rows.forEach { row ->
                row.words.forEach { word ->
                    word.text().forEach { char ->
                        item { CharCell(char) }
                    }
                }
            }
        }
    }
}
