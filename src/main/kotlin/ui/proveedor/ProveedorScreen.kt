package ui.proveedor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ProveedorScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Icon(painter = painterResource("icons/proveedor.png"), contentDescription = null, modifier = Modifier.fillMaxSize())
    }

    // Proveedor list screen
    ProveedorList()

    // Productos Proveedor list screen
    ProductosProveedorList()

    // Add Proveedor screen
    AddProveedorScreen()

    // EditProveedorScreen()
    EditProveedorScreen()
}