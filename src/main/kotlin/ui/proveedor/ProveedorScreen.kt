package ui.proveedor

import androidx.compose.runtime.Composable

@Composable
fun ProveedorScreen() {
    // Proveedor list screen
    ProveedorList()

    // Productos Proveedor list screen
    ProductosProveedorList()

    // Add Proveedor screen
    AddProveedorScreen()

    // EditProveedorScreen()
    EditProveedorScreen()
}