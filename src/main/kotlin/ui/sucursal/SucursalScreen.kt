package ui.sucursal

import androidx.compose.runtime.Composable

@Composable
fun SucursalScreen() {
    // Sucursal list screen
    SucursalList()

    // Add sucursal screen
    AddSucursalScreen()

    // Edit sucursal screen
    EditSucursalScreen()
}