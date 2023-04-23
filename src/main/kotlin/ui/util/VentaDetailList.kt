package ui.util

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
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
import data.model.DetalleVentaProducto
import util.decimalFormat


@Composable
fun VentaList(ventasList: List<DetalleVentaProducto>, showEmpleado: Boolean = true, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize().border(width = Dp.Hairline, color = Color.Gray)) {
            VentaListHeader(showEmpleado)
            VentaListContent(ventaList = ventasList, showEmpleado = showEmpleado, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun VentaListHeader(showEmpleado: Boolean) {
    Surface(elevation = 8.dp) {
        Column {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(
                    text = "id",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Productos",
                    modifier = Modifier.weight(3f),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Fecha",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                if(showEmpleado) {
                    Text(
                        text = "Empleado",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = "Total",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun VentaListContent(ventaList: List<DetalleVentaProducto>, showEmpleado: Boolean, modifier: Modifier = Modifier) {
    Box {
        val state = rememberLazyListState()

        LazyColumn(modifier = modifier, state = state) {
            items(ventaList) {
                VentaListContentItem(it, showEmpleado)
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
private fun VentaListContentItem(venta: DetalleVentaProducto, showEmpleado: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
        Text(
            text = "${venta.venta.id}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Column(modifier = Modifier.weight(3f)) {
            for (index in 0 until venta.productos.size) {
                Text(
                    text = venta.productos[index].nombre,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
        Text(
            text = venta.venta.fechaVentaTexto,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        if(showEmpleado) {
            Text(
                text = venta.venta.empleado.nombre,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "$ ${decimalFormat(venta.venta.netoVenta)}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}