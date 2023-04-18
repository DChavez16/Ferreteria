package ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import data.model.Producto
import data.model.ProductoTestList
import util.decimalFormat
import ui.util.AvailableProductsList
import ui.util.BottomButtons
import kotlin.random.Random


@Composable
fun HomeScreen() {
    var selectedProductos by remember { mutableStateOf(listOf<SelectedProductos>()) }
    val saleInfo by remember { mutableStateOf(SaleInfo(0.0, 0.0, 0.0)) }

    Surface(color = MaterialTheme.colors.background) {
        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxHeight().padding(16.dp)) {
            Row(Modifier.fillMaxWidth().weight(1f)) {
                // Included products in the sell
                SelectedProductsList(Modifier.weight(2f), selectedProductos, saleInfo)
                Spacer(Modifier.width(16.dp))

                // Available products for sale
                AvailableProductsList(Modifier.weight(1f), ProductoTestList, quantitySelectionEnabled = true) { producto, quantity ->
                    selectedProductos = selectedProductos.toMutableList().addProducto(
                        producto, quantity!!
                    )
                    saleInfo.update(selectedProductos)
                }
            }

            // Bottom buttons to interact with the program
            BottomButtons(
                twoButtons = true,
                firstButtonText = "Aceptar",
                firstButtonAction = {},
                secondButtonText = "Limpiar campos",
                secondButtonAction = { selectedProductos = emptyList() },
                firstButtonEnabled = selectedProductos.isNotEmpty()
            )
        }
    }
}

@Composable
private fun SelectedProductsList(
    modifier: Modifier = Modifier, selectedProductosList: List<SelectedProductos>, saleInfo: SaleInfo
) {
    Column(modifier = modifier.fillMaxHeight()) {
        // Selected products list header
        SelectedProductListHeader()
        Divider(color = Color.Gray, thickness = Dp.Hairline, modifier = Modifier.padding(horizontal = 4.dp))
        // Selected products list content
        LazyColumn(modifier.weight(1f).padding(8.dp)) {
            items(selectedProductosList) {
                SelectedProductListContent(it)
            }
        }
        // Sale info
        SelectedProductListSaleInfo(saleInfo)
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
private fun SelectedProductListContent(selectedProducto: SelectedProductos) {
    val precioVenta = with(selectedProducto) {
        (producto.precioVenta * cantidad) - (producto.precioVenta * cantidad * descuento)
    }

    Column(modifier = Modifier.padding(bottom = 4.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = selectedProducto.producto.nombre, modifier = Modifier.weight(3f), textAlign = TextAlign.Left)
            Text(
                text = "${selectedProducto.cantidad}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = decimalFormat(precioVenta),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        if (selectedProducto.descuento > 0.0) {
            Text(
                text = "Descuento aplicado (${(selectedProducto.descuento * 100).toInt()} %)",
                color = Color.Red,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
private fun SelectedProductListSaleInfo(saleInfo: SaleInfo) {
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        // Sale subtotal
        Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Subtotal:", style = MaterialTheme.typography.body1)
            Spacer(Modifier.width(5.dp))
            Text(text = decimalFormat(saleInfo.subTotal), style = MaterialTheme.typography.body2)
        }
        // Sale discount
        Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Descuento:", style = MaterialTheme.typography.body1)
            Spacer(Modifier.width(5.dp))
            Text(
                text = if (saleInfo.descuento > 0.0) decimalFormat(saleInfo.descuento) else "~",
                style = MaterialTheme.typography.body2
            )
        }
        // Sale total
        Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Total:", style = MaterialTheme.typography.body1)
            Spacer(Modifier.width(5.dp))
            Text(text = decimalFormat(saleInfo.total), style = MaterialTheme.typography.body2)
        }
    }
}


/*
Helper methods
*/

private fun getDescuento() = if (Random.nextBoolean()) ListaDescuentos[(0..4).random()] else 0.0

/**
 * Add a product and quantity into the selected products list.
 * If the product is already in the list, the list element where is located will update its quantity.
 * If the product is not in the list, the product will be added to the list
 */
private fun MutableList<SelectedProductos>.addProducto(
    producto: Producto, quantityValue: Int
): List<SelectedProductos> {
    var isInTheList = false

    // Validate if is already in the list, if so, update the element content
    this.forEachIndexed { index, selectedProductos ->
        if (producto.id == selectedProductos.producto.id) {
            isInTheList = true
            this[index] = SelectedProductos(producto, quantityValue, getDescuento())
        }
    }

    if (!isInTheList) this.add(SelectedProductos(producto, quantityValue, getDescuento()))

    return this
}

/**
 * Navigates within selectedProductosList to calculate the SaleInfo's subtotal, descuento and total
 */
private fun SaleInfo.update(selectedProductosList: List<SelectedProductos>) {
    var newSubtotal = 0.0
    var newDescuento = 0.0

    selectedProductosList.forEach { selectedProducto ->
        newSubtotal += selectedProducto.producto.precioVenta * selectedProducto.cantidad
        newDescuento += selectedProducto.producto.precioVenta * selectedProducto.cantidad * selectedProducto.descuento
    }

    this.subTotal = newSubtotal
    this.descuento = newDescuento
    this.total = newSubtotal - newDescuento
}

private data class SelectedProductos(
    val producto: Producto, val cantidad: Int, val descuento: Double
)

private data class SaleInfo(
    var subTotal: Double, var descuento: Double, var total: Double = subTotal - descuento
)

private val ListaDescuentos = listOf(
    0.05, 0.10, 0.15, 0.20, 0.25
)