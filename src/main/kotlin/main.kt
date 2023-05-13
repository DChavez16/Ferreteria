
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ui.mainContainer.MainContainer
import ui.util.theme.FerreteriaTheme
import java.sql.Connection
import java.sql.DriverManager

fun main() = application {
    Window(
        onCloseRequest = { exitApplication() },
        title = "Ferreteria",
        state = WindowState(placement = WindowPlacement.Fullscreen),
        resizable = false,
        icon = painterResource("images/logo_icon.png")
    ) {
        FerreteriaTheme {
            MainContainer { run { exitApplication(); Database.connection.close() } }
        }
    }
}


object Database {
    private const val ip = "DANIEL:1433"
    private const val db = "ferreteriaBdyLEJ23"
    private const val url = "jdbc:sqlserver://localhost\\\\$ip;databaseName=$db;integratedSecurity=true;encrypt=true;trustServerCertificate=true"
    var connection: Connection = DriverManager.getConnection(url)
}

