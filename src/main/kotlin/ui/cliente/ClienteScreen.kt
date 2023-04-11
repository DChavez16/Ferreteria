package ui.cliente

import androidx.compose.runtime.Composable

@Composable
fun ClienteScreen() {
    // Cliente list screen
    ClienteList()

    // Venta Cliente list screen
    VentaClienteList()

    // Add Cliente screen
    AddClienteScreen()

    // Edit Cliente screen
    EditClienteScreen()
}