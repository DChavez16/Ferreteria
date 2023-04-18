
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ui.mainContainer.MainContainer

fun main() = application {
    Window(
        onCloseRequest = { exitApplication() },
        title = "Ferreteria",
        state = WindowState(placement = WindowPlacement.Fullscreen),
        resizable = false
    ) {
        MaterialTheme {
            MainContainer { exitApplication() }
        }
    }
}
