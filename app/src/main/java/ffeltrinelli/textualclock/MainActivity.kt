package ffeltrinelli.textualclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ffeltrinelli.textualclock.domain.clock.english.EnglishClock
import ffeltrinelli.textualclock.ui.composable.TextualClock
import ffeltrinelli.textualclock.ui.theme.TextualClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextualClockTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextualClock(EnglishClock.INSTANCE, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextualClockPreview() {
    TextualClockTheme {
        TextualClock(EnglishClock.INSTANCE)
    }
}
