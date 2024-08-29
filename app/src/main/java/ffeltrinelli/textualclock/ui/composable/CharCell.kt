package ffeltrinelli.textualclock.ui.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CharCell(char: Char) {
    val cellModifier = Modifier
        .padding(2.dp)
        .border(width = 2.dp, color = Color.Black)
    Text(
        text = char.toString(),
        modifier = cellModifier,
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}