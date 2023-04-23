package ui.proveedor

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
import data.model.Proveedor
import ui.util.BottomButtons

@Composable
fun ProveedorList(
    proveedorList: List<Proveedor>,
    onAddProveedorClick: () -> Unit,
    onEdirtProveedorClick: (Proveedor) -> Unit,
    onProveedorElementClick: (Proveedor) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        Surface(modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.fillMaxSize().border(width = Dp.Hairline, color = Color.Gray)) {
                ProveedorListHeader()
                ProveedorListContent(
                    proveedorList = proveedorList,
                    onProveedorElementClick = onProveedorElementClick,
                    onEditProveedorClick = onEdirtProveedorClick
                )
            }
        }

        BottomButtons(
            twoButtons = false,
            firstButtonText = "Agregar proveedor",
            firstButtonAction = onAddProveedorClick
        )
    }
}

@Composable
private fun ProveedorListHeader() {
    Surface(elevation = 8.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "id",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Proveedor",
                modifier = Modifier.weight(2.5f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Correo",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Telefono",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun ProveedorListContent(
    proveedorList: List<Proveedor>,
    onProveedorElementClick: (Proveedor) -> Unit,
    onEditProveedorClick: (Proveedor) -> Unit
) {
    Box {
        val state = rememberLazyListState()

        LazyColumn(state = state) {
            items(proveedorList) {
                ProveedorListItem(it, onProveedorElementClick, onEditProveedorClick)
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
private fun ProveedorListItem(
    proveedor: Proveedor,
    onProveedorElementClick: (Proveedor) -> Unit,
    onEditProveedorClick: (Proveedor) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onProveedorElementClick(proveedor) }.padding(16.dp)
    ) {
        Text(
            text = "${proveedor.id}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Text(
            text = proveedor.nombre,
            modifier = Modifier.weight(2.5f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Start
        )
        Text(
            text = proveedor.contacto.correo,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Text(
            text = proveedor.contacto.telefono,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        IconButton(
            modifier = Modifier.weight(0.5f).height(20.dp),
            onClick = { onEditProveedorClick(proveedor) }
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}