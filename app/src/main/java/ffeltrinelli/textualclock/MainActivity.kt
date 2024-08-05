package ffeltrinelli.textualclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ffeltrinelli.textualclock.ui.theme.TextualClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextualClockTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextualClock(ClockMatrix.ENGLISH_CLOCK, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TextualClock(clockMatrix: ClockMatrix, modifier: Modifier = Modifier) {
    Box(
        modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(clockMatrix.maxRowChars()),
            modifier = modifier
        ) {
            clockMatrix.rows().forEach { rowSymbols ->
                rowSymbols.forEach { symbol ->
                    symbol.text().forEach { char ->
                        item { CharCell(char) }
                    }
                }
            }
        }
    }
}

@Composable
fun CharCell(char: Char) {
    val cellModifier = Modifier
        .padding(2.dp)
        .border(width = 2.dp, color = Color.Black)
    Text(text = char.toString(),
        modifier = cellModifier,
        fontSize = 30.sp,
        textAlign = TextAlign.Center)
}

@Preview(showBackground = true)
@Composable
fun TextualClockPreview() {
    TextualClockTheme {
        TextualClock(ClockMatrix.ENGLISH_CLOCK)
    }
}
