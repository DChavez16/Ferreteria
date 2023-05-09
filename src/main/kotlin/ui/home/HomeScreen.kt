package ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import controller.home.HomeController
import controller.home.SaleInfo
import controller.home.SelectedProductos
import ui.util.AvailableProductsList
import ui.util.BottomButtons
import util.decimalFormat


@Composable
fun HomeScreen() {
    val homeController = HomeController()

    val homeState = homeController.homeState.collectAsState()

    Surface(color = MaterialTheme.colors.background) {
        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxHeight().padding(16.dp)) {
            Row(Modifier.fillMaxWidth().weight(1f)) {
                // Included products in the sell
                SelectedProductsList(Modifier.weight(2f), homeState.value.selectedProductos, homeState.value.saleInfo)
                Spacer(Modifier.width(16.dp))

                // Available products for sale
                AvailableProductsList(
                    modifier = Modifier.weight(1f),
                    productoList = homeController.productsList,
                    quantitySelectionEnabled = true,
                    onAddProductoClick = { producto, quantity ->
                        homeController.onAddProductoClick(producto, quantity)
                    }
                )
            }

            // Bottom buttons to interact with the program
            BottomButtons(
                twoButtons = true,
                firstButtonText = "Aceptar",
                firstButtonAction = {},
                secondButtonText = "Limpiar campos",
                secondButtonAction = { homeController.resetState() },
                firstButtonEnabled = homeController.selectedProductsListIsNotEmpty()
            )
        }
    }
}

@Composable
private fun SelectedProductsList(
    modifier: Modifier = Modifier, selectedProductos: List<SelectedProductos>, saleInfo: SaleInfo
) {
    Column(modifier = modifier.fillMaxHeight()) {
        // Selected products list header
        SelectedProductListHeader()
        Divider(color = Color.Gray, thickness = Dp.Hairline, modifier = Modifier.padding(horizontal = 4.dp))
        // Selected products list content
        LazyColumn(modifier.weight(1f).padding(8.dp)) {
            items(selectedProductos) {
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
            style = MaterialTheme.typography.h5,
            modifier = Modifier.weight(3f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Cantidad",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Precio",
            style = MaterialTheme.typography.h5,
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
            Text(
                text = selectedProducto.producto.nombre,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(3f),
                textAlign = TextAlign.Left
            )
            Text(
                text = "${selectedProducto.cantidad}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = decimalFormat(precioVenta),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        if (selectedProducto.descuento > 0.0) {
            Text(
                text = "Descuento aplicado (${(selectedProducto.descuento * 100).toInt()} %)",
                color = Color.Red,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
private fun SelectedProductListSaleInfo(saleInfo: SaleInfo) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        // Sale subtotal
        Row(
            modifier = Modifier.padding(horizontal = 10.dp).weight(1f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            Text("Subtotal: $", style = MaterialTheme.typography.h6)
            Spacer(Modifier.width(5.dp))
            Text(text = decimalFormat(saleInfo.subTotal), style = MaterialTheme.typography.body1)
        }
        // Sale IVA
        Row(
            modifier = Modifier.padding(horizontal = 10.dp).weight(1f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            Text("IVA: $", style = MaterialTheme.typography.h6)
            Spacer(Modifier.width(5.dp))
            Text(text = decimalFormat(saleInfo.incrementoIVA), style = MaterialTheme.typography.body1)
        }
        // Sale discount
        Row(
            modifier = Modifier.padding(horizontal = 10.dp).weight(1f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            Text("Descuento: $", style = MaterialTheme.typography.h6)
            Spacer(Modifier.width(5.dp))
            Text(
                text = if (saleInfo.descuento > 0.0) decimalFormat(saleInfo.descuento) else "~",
                style = MaterialTheme.typography.body1
            )
        }
        // Sale total
        Row(
            modifier = Modifier.padding(horizontal = 10.dp).weight(1f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            Text("Total: $", style = MaterialTheme.typography.h6)
            Spacer(Modifier.width(5.dp))
            Text(text = decimalFormat(saleInfo.total), style = MaterialTheme.typography.body1)
        }
    }
}