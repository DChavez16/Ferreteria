package ui.util.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

private val DarkColorPalette = darkColors(
    background = White,
    onBackground = Black,
    surface = Color(0xFF334765),
    onSurface = White,
    primary = Color(0xFF082032),
    onPrimary = White,
    primaryVariant = Color(0xFF2c394b),
    secondary = Color(0xFFe9791a),
    onSecondary = Black,
    secondaryVariant = Color(0xFFff9a3c)
)

private val LightColorPalette = lightColors(
    background = White,
    onBackground = Black,
    surface = Color(0xFF334765),
    onSurface = White,
    primary = Color(0xFF082032),
    onPrimary = White,
    primaryVariant = Color(0xFF2c394b),
    secondary = Color(0xFFe9791a),
    onSecondary = Black,
    secondaryVariant = Color(0xFFff9a3c)
)

@Composable
fun FerreteriaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
