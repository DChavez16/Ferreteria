package ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import data.model.Producto
import data.model.ProductoTestList
import ui.util.BottomButtons
import java.text.DecimalFormat
import kotlin.random.Random

// TODO Add functionality

@Composable
fun HomeScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxHeight().padding(16.dp)) {
            Row(Modifier.fillMaxWidth().weight(1f)) {
                // Productos incluidos en la venta
                SelectedProductsList(Modifier.weight(2f), ProductoTestList)
                Spacer(Modifier.width(16.dp))

                // Productos disponibles en el inventario
                AvailableProductsList(Modifier.weight(1f), ProductoTestList)
            }

            // Botones
            BottomButtons(
                twoButtons = true,
                firstButtonText = "Aceptar",
                firstButtonAction = {},
                secondButtonText = "Limpiar campos",
                secondButtonAction = {},
                firstButtonEnabled = false
            )
        }
    }
}

@Composable
private fun SelectedProductsList(modifier: Modifier = Modifier, productoList: List<Producto>) {
    Column(modifier = modifier.fillMaxHeight()) {
        // Selected products list header
        SelectedProductListHeader()
        Divider(color = Color.Gray, thickness = Dp.Hairline, modifier = Modifier.padding(horizontal = 4.dp))
        // Selected products list content
        LazyColumn(modifier.weight(1f).padding(8.dp)) {
            items(productoList) {
                SelectedProductListContent(it)
            }
        }
        // Sale info
        SelectedProductListSaleInfo()
    }
}

@Composable
private fun SelectedProductListHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = "Producto",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.weight(3f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Cantidad",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Precio",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SelectedProductListContent(producto: Producto) {
    Column(modifier = Modifier.padding(bottom = 4.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = producto.nombre, modifier = Modifier.weight(3f), textAlign = TextAlign.Left)
            Text(
                text = "${(1..5).random()}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = "${producto.precioVenta}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        if (Random.nextBoolean()) {
            Text(
                text = "Descuento aplicado",
                color = Color.Red,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
private fun SelectedProductListSaleInfo() {
    // Placeholder info
    val df = DecimalFormat("#.##")
    val subtotal = Random.nextDouble(from = 1.0, until = 1000.0)
    val descuento = if (Random.nextBoolean()) Random.nextDouble(from = 5.0, until = subtotal / 2.0) else 0.0
    val total = if (descuento > 0.0) subtotal.minus(descuento) else subtotal

    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        // Sale subtotal
        Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Subtotal:", style = MaterialTheme.typography.body1)
            Spacer(Modifier.width(5.dp))
            Text(text = df.format(subtotal), style = MaterialTheme.typography.body2)
        }
        // Sale discount
        Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Descuento:", style = MaterialTheme.typography.body1)
            Spacer(Modifier.width(5.dp))
            Text(
                text = if (descuento > 0.0) df.format(descuento) else "~",
                style = MaterialTheme.typography.body2
            )
        }
        // Sale total
        Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Total:", style = MaterialTheme.typography.body1)
            Spacer(Modifier.width(5.dp))
            Text(text = df.format(total), style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
private fun AvailableProductsList(modifier: Modifier = Modifier, productoList: List<Producto>) {
    Column(modifier = modifier.fillMaxHeight()) {
        Text(text = "Seleccionar cliente plaheholder")
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.weight(1f)
        ) {
            items(productoList) {
                AvailableProductsListItem(it)
            }
        }
    }
}

@Composable
private fun AvailableProductsListItem(producto: Producto) {
    Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource("icons/producto.png"),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = producto.nombre,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {}, modifier = Modifier.weight(1f)) {
                    Icon(painterResource("icons/minus.png"), contentDescription = null, modifier = Modifier.padding(8.dp))
                }
                Text(
                    text = "1",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.weight(2f).fillMaxHeight(),
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = {}, modifier = Modifier.weight(1f)) {
                    Icon(painterResource("icons/add.png"), contentDescription = null, modifier = Modifier.padding(8.dp))
                }
            }
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Agregar", textAlign = TextAlign.Center, style = MaterialTheme.typography.button)
            }
        }
    }
}