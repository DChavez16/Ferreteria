import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.empleado.Empleado
import model.sucursal.Sucursal
import ui.login.LoginWindow
import ui.mainContainer.MainContainer
import ui.util.theme.FerreteriaTheme
import java.sql.Connection
import java.sql.DriverManager

fun main() = application {
    val programState = ProgramEscencials.programState.collectAsState()

    if (programState.value.loginWindow) {
        Window(
            onCloseRequest = { exitApplication() }, title = "Ferreteria", state = WindowState(
                placement = WindowPlacement.Floating,
                position = WindowPosition(alignment = Alignment.Center),
                size = DpSize(width = 1000.dp, height = 500.dp)
            ), resizable = false, icon = painterResource("images/logo_icon.png")
        ) {
            FerreteriaTheme {
                LoginWindow()
            }
        }
    } else {
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
}


object Database {
    private const val ip = "DANIEL:1433"
    private const val db = "ferreteriaBdyLEJ23"
    private const val url =
        "jdbc:sqlserver://localhost\\\\$ip;databaseName=$db;integratedSecurity=true;encrypt=true;trustServerCertificate=true"
    var connection: Connection = DriverManager.getConnection(url)
}

object ProgramEscencials {
    private var _programState = MutableStateFlow(ProgramState())
    val programState: StateFlow<ProgramState> = _programState.asStateFlow()

    var selectedSucursal: Sucursal = Sucursal()
    var selectedEmpleado: Empleado = Empleado()

    fun reset() {
        selectedSucursal = Sucursal()
        selectedEmpleado = Empleado()
    }

    fun updateSucursal(sucursal: Sucursal) {
        selectedSucursal = sucursal
    }

    fun updateEmpleado(empleado: Empleado) {
        selectedEmpleado = empleado
    }

    fun changeWindow() {
        _programState.update { currentState ->
            currentState.copy(loginWindow = !currentState.loginWindow)
        }
    }

    data class ProgramState(
        var loginWindow: Boolean = true
    )
}