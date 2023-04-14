package ui.empleado

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun EmpleadoScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Icon(painter = painterResource("icons/empleado.png"), contentDescription = null, modifier = Modifier.fillMaxSize())
    }

    // Empleado list screen
    EmpleadoList()

    // Venta Empleado list screen
    VentaEmpleadoList()

    // Add Empleado screen
    AddEmpleadoScreen()

    // Edit Empleado screen
    EditEmpleadoScreen()
}