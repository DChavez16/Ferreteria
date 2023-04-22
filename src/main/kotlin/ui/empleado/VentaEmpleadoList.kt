package ui.empleado

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.model.Empleado

@Composable
fun VentaEmpleadoList(empleado: Empleado, onReturnButtonClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Ventas de empleado")
            Text("Regresar", modifier = Modifier.clickable { onReturnButtonClick() })
        }
    }
}