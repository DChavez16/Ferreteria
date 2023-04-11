import ui.mainContainer.MainContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.*

fun main() = application {
    Window(
        onCloseRequest = { exitApplication() },
        title = "Ferreteria",
        state = WindowState(placement = WindowPlacement.Maximized)
    ) {
        MaterialTheme {
            MainContainer()
        }
    }
}