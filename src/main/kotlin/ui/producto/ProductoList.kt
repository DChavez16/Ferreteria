package ui.producto

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
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
import data.model.Producto
import ui.util.BottomButtons

@Composable
fun ProductoList(productoList: List<Producto>, onClickA: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        Surface(modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.fillMaxSize().border(width = Dp.Hairline, color = Color.Gray)) {
                ProductoListHeader()
                ProductoListContent(
                    productoList = productoList,
                    modifier = Modifier.weight(1f),
                    onItemEditClick = onClickA
                )
            }
        }
        BottomButtons(
            twoButtons = false,
            firstButtonText = "Agregar",
            firstButtonAction = onClickA
        )
    }
}

@Composable
private fun ProductoListHeader() {
    Surface(elevation = 8.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "id",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Producto",
                modifier = Modifier.weight(2f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Descripcion",
                modifier = Modifier.weight(2.5f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Precio",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun ProductoListContent(productoList: List<Producto>, modifier: Modifier = Modifier, onItemEditClick: () -> Unit) {
    Box {
        val state = rememberLazyListState()

        LazyColumn(modifier = modifier, state = state) {
            items(productoList) {
                ProductoListContentItem(it, onItemEditClick)
                Divider(color = Color.Gray, thickness = Dp.Hairline)
            }
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(scrollState = state)
        )
    }
}

@Composable
private fun ProductoListContentItem(producto: Producto, onItemEditClick: () -> Unit) {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "${producto.id}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Text(
            text = producto.nombre,
            modifier = Modifier.weight(2f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Left
        )
        Text(
            text = producto.descripcion,
            modifier = Modifier.weight(2.5f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Left
        )
        Text(
            text = "${producto.precioVenta}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        IconButton(modifier = Modifier.weight(0.5f).height(20.dp), onClick = onItemEditClick) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}