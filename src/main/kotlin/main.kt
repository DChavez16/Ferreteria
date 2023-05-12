
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ui.mainContainer.MainContainer
import ui.util.theme.FerreteriaTheme

fun main() = application {
    Window(
        onCloseRequest = { exitApplication() },
        title = "Ferreteria",
        state = WindowState(placement = WindowPlacement.Fullscreen),
        resizable = false,
        icon = painterResource("images/logo_icon.png")
    ) {
        FerreteriaTheme {
            MainContainer { exitApplication() }
        }
    }
}
