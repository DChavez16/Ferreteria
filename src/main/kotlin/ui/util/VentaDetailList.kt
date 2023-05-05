package ui.util

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import data.model.DetalleVentaProducto
import util.decimalFormat
import util.getFechaString


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
                    text = "Id",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Productos",
                    modifier = Modifier.weight(3f),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Fecha",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
                if (showEmpleado) {
                    Text(
                        text = "Empleado",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = "Total",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun VentaListContent(
    ventaList: List<DetalleVentaProducto>, showEmpleado: Boolean, modifier: Modifier = Modifier
) {
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
private fun VentaListContentItem(detalleVenta: DetalleVentaProducto, showEmpleado: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
        Text(
            text = "${detalleVenta.venta.id}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        VentaProducts(detalleVenta = detalleVenta, modifier = Modifier.weight(3f))
        Text(
            text = getFechaString(detalleVenta.venta.fechaVenta),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        if (showEmpleado) {
            Text(
                text = detalleVenta.venta.empleado.nombre,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "$ ${decimalFormat(detalleVenta.venta.netoVenta)}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun VentaProducts(detalleVenta: DetalleVentaProducto, modifier: Modifier = Modifier) {
    var expandedVentaProducts by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        // Header of the products included in the sale
        VentaProductsHeader(expandedVentaProducts = expandedVentaProducts,
            productosListSize = detalleVenta.productos.size,
            onExpandButtonClick = { expandedVentaProducts = it })
        if (expandedVentaProducts) {
            Divider(color = Color.White, thickness = Dp.Hairline)
            for (index in 0 until detalleVenta.productos.size) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = detalleVenta.productos[index].producto.nombre,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(vertical = 4.dp).weight(2f)
                    )
                    Text(
                        text = "${detalleVenta.productos[index].cantidad}",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp).weight(1f)
                    )
                    Text(
                        text = "$ ${decimalFormat(detalleVenta.productos[index].precioVenta)}",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp).weight(1f)
                    )
                    Spacer(Modifier.width(30.dp))
                }
            }
        }
    }
}

@Composable
private fun VentaProductsHeader(
    expandedVentaProducts: Boolean, productosListSize: Int, onExpandButtonClick: (Boolean) -> Unit
) {
    val icon = painterResource("icons/${if (expandedVentaProducts) "expand_less" else "expand_more"}.png")

    Surface(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // The content of the header changes depending on if the element is expanded or contracted
            if (expandedVentaProducts) {
                Text(
                    text = "Producto",
                    modifier = Modifier.weight(2f),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Cantidad",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Total",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = "$productosListSize producto(s) vendido(s)",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.weight(1f)
                )
            }

            // Expanded element indicator icon
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.size(30.dp).clickable { onExpandButtonClick(!expandedVentaProducts) }
            ) {
                Icon(
                    painter = icon, contentDescription = null
                )
            }
        }
    }
}