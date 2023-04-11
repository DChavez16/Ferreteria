package ui.empleado

import androidx.compose.runtime.Composable

@Composable
fun EmpleadoScreen() {
    // Empleado list screen
    EmpleadoList()

    // Venta Empleado list screen
    VentaEmpleadoList()

    // Add Empleado screen
    AddEmpleadoScreen()

    // Edit Empleado screen
    EditEmpleadoScreen()
}