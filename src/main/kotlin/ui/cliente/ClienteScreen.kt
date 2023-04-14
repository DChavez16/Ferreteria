package ui.cliente

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ClienteScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Icon(painter = painterResource("icons/cliente.png"), contentDescription = null, modifier = Modifier.fillMaxSize())
    }

    // Cliente list screen
    ClienteList()

    // Venta Cliente list screen
    VentaClienteList()

    // Add Cliente screen
    AddClienteScreen()

    // Edit Cliente screen
    EditClienteScreen()
}