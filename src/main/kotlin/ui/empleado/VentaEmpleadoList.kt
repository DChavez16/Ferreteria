package ui.empleado

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import model.empleado.Empleado
import model.empleado.getDetalleVentasEmpleado
import ui.util.ScreenHeader
import ui.util.VentaList

@Composable
fun VentaEmpleadoList(empleado: Empleado, onReturnButtonClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Screen Header
        ScreenHeader(headerTitle = "Ventas del empleado", onReturnButtonClick = onReturnButtonClick)

        // Empleado Details
        EmpleadoDetails(empleado = empleado, modifier = Modifier.padding(16.dp))

        // Ventas empleado list
        VentaList(
            ventasList = getDetalleVentasEmpleado(empleado.id),
            showEmpleado = false,
            modifier = Modifier.weight(1f).padding(16.dp)
        )
    }
}

@Composable
private fun EmpleadoDetails(empleado: Empleado, modifier: Modifier = Modifier) {
    // Row of employee's id, name and sucursal
    Row(modifier = modifier.fillMaxWidth()) {
        // Row of id details
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Id: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = "${empleado.id}",
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
        }

        // Row of name details
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Nombre: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = empleado.nombre,
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
        }

        // Row of sucursal details
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Sucursal: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = empleado.sucursal.name,
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}