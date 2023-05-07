package ui.sucursal

import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
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
import model.sucursal.Sucursal
import ui.util.BottomButtons

@Composable
fun SucursalList(
    sucursalList: List<Sucursal>,
    onAddSucursalClick: () -> Unit,
    onEditSucursalClick: (Sucursal) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        // Sucursal list surface
        Surface(modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.fillMaxSize().border(width = Dp.Hairline, color = Color.Gray)) {
                SucursalListHeader()
                SucursalListContent(
                    sucursalList = sucursalList,
                    onEditSucursalClick = onEditSucursalClick
                )
            }
        }

        // Bottom buttons
        BottomButtons(
            twoButtons = false,
            firstButtonText = "Agregar sucursal",
            firstButtonAction = onAddSucursalClick
        )
    }
}

@Composable
private fun SucursalListHeader() {
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
                text = "Dirección",
                modifier = Modifier.weight(2.5f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Teléfono",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun SucursalListContent(
    sucursalList: List<Sucursal>,
    onEditSucursalClick: (Sucursal) -> Unit
) {
    Box {
        val state = rememberLazyListState()

        LazyColumn(state = state) {
            items(sucursalList) {
                SucursalListItem(it, onEditSucursalClick)
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
private fun SucursalListItem(
    sucursal: Sucursal,
    onEditSucursalClick: (Sucursal) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "${sucursal.id}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = sucursal.name,
            modifier = Modifier.weight(2f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start
        )
        Text(
            text = sucursal.contacto.direccion.formato,
            modifier = Modifier.weight(2.5f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = sucursal.contacto.telefono,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        IconButton(
            modifier = Modifier.weight(0.5f).height(20.dp),
            onClick = { onEditSucursalClick(sucursal) }
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}