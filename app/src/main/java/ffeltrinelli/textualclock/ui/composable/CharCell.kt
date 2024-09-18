package ffeltrinelli.textualclock.ui.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ffeltrinelli.textualclock.ui.theme.GreyLight

@Composable
fun CharCell(char: Char, isHighlighted: Boolean) {
    Text(
        text = char.toString(),
        color = if (isHighlighted) Color.White else GreyLight,
        fontWeight = if (isHighlighted) FontWeight.Medium else FontWeight.Normal,
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}
