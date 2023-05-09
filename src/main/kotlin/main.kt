
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
        state = WindowState(placement = WindowPlacement.Maximized),
        resizable = false
    ) {
        FerreteriaTheme {
            MainContainer { exitApplication() }
        }
    }
}
