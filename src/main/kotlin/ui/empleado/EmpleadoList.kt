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
import data.model.EmpleadoTestList

@Composable
fun EmpleadoList(
    empleadoList: List<Empleado>,
    onAddEmpleadoClick: () -> Unit,
    onEditEmpleadoClick: (Empleado) -> Unit,
    onEmpleadoElementClick: (Empleado) -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Lista empleados")
            Text("Agregar", modifier = Modifier.clickable { onAddEmpleadoClick() })
            Text("Editar", modifier = Modifier.clickable { onEditEmpleadoClick(EmpleadoTestList[0]) })
            Text("Empleado", modifier = Modifier.clickable { onEmpleadoElementClick(EmpleadoTestList[0]) })
        }
    }
}