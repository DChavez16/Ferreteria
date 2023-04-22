package ui.util

import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import data.model.Producto
import util.decimalFormat

@Composable
fun ProductosProveedorDetailsList(productosList: List<Producto>, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxHeight().border(width = Dp.Hairline, color = Color.Gray)) {
            ProductosProveedorListHeader()
            ProductosProveedorListContent(productosList = productosList)
        }
    }
}

@Composable
private fun ProductosProveedorListHeader() {
    Surface(elevation = 8.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "id",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Producto",
                modifier = Modifier.weight(2f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Descripcion",
                modifier = Modifier.weight(2f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Precio de venta",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ProductosProveedorListContent(productosList: List<Producto>) {
    Box {
        val state = rememberLazyListState()

        LazyColumn(state = state) {
            items(productosList) {
                ProductosProveedorListItem(it)
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
private fun ProductosProveedorListItem(producto: Producto) {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
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
            textAlign = TextAlign.Start
        )
        Text(
            text = producto.descripcion,
            modifier = Modifier.weight(2f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Start
        )
        Text(
            text = "$ ${decimalFormat(producto.precioVenta)}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}