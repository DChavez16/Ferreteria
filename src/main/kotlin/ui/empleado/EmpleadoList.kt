package ui.empleado

import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import model.empleado.Empleado
import model.empleado.getVentasEmpleado
import ui.util.BottomButtons
import util.decimalFormat

@Composable
fun EmpleadoList(
    empleadoList: List<Empleado>,
    onAddEmpleadoClick: () -> Unit,
    onEditEmpleadoClick: (Empleado) -> Unit,
    onEmpleadoElementClick: (Empleado) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        Surface(modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.fillMaxSize().border(width = Dp.Hairline, color = Color.Gray)) {
                // List header
                EmpleadoListHeader()
                // List content
                EmpleadoListContent(
                    empleadoList = empleadoList,
                    onEmpleadoElementClick = onEmpleadoElementClick,
                    onEditEmpleadoClick = onEditEmpleadoClick
                )
            }
        }

        BottomButtons(
            twoButtons = false,
            firstButtonText = "Agregar Empleado",
            firstButtonAction = onAddEmpleadoClick
        )
    }
}

@Composable
private fun EmpleadoListHeader() {
    Surface(elevation = 8.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "Id",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Nombre",
                modifier = Modifier.weight(2f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Sueldo",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Ventas",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Puesto",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Sucursal",
                modifier = Modifier.weight(1.5f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun EmpleadoListContent(
    empleadoList: List<Empleado>,
    onEmpleadoElementClick: (Empleado) -> Unit,
    onEditEmpleadoClick: (Empleado) -> Unit
) {
    Box {
        val state = rememberLazyListState()

        LazyColumn(state = state) {
            items(empleadoList) {
                EmpleadoListItem(it, onEmpleadoElementClick, onEditEmpleadoClick)
                Divider(color = Color.Gray, thickness = Dp.Hairline)
            }
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd),
            adapter = ScrollbarAdapter(scrollState = state)
        )
    }
}

@Composable
private fun EmpleadoListItem(
    empleado: Empleado,
    onEmpleadoElementClick: (Empleado) -> Unit,
    onEditEmpleadoClick: (Empleado) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onEmpleadoElementClick(empleado) }.padding(16.dp)
    ) {
        Text(
            text = "${empleado.id}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = empleado.nombre,
            modifier = Modifier.weight(2f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start
        )
        Text(
            text = "$ ${decimalFormat(empleado.sueldo)}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${getVentasEmpleado(empleado.id).size}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = empleado.puesto.text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start
        )
        Text(
            text = empleado.sucursal.name,
            modifier = Modifier.weight(1.5f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start
        )
        IconButton(
            modifier = Modifier.weight(0.5f).height(20.dp),
            onClick = { onEditEmpleadoClick(empleado) }
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}