package ui.promocion

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import data.model.Producto
import data.model.ProductoTestList
import data.model.Promocion
import ui.util.AvailableProductsList
import ui.util.BottomButtons
import ui.util.ScreenHeader

@Composable
fun PromocionInfoScreen(
    editPromocion: Boolean,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: (Promocion) -> Unit,
    promocion: Promocion? = null,
    onDeleteClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Draws the screen header, the header's title varies with the defined screen mode
        ScreenHeader(
            headerTitle = if (editPromocion) "Editar" else "Agregar", onReturnButtonClick = onReturnButtonClick
        )

        // Draws a form that defines the values of the current promotion
        PromocionForm(
            editPromocion = editPromocion,
            promocion = promocion,
            onMainButtonClick = onMainButtonClick,
            onDeleteClick = onDeleteClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun PromocionForm(
    editPromocion: Boolean,
    promocion: Promocion?,
    onMainButtonClick: (Promocion) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var promocionDescription by remember { mutableStateOf(promocion?.description ?: "") }
    var promocionDiscount by remember { mutableStateOf("${promocion?.descuento}" ?: "") }
    var promocionAvailability by remember { mutableStateOf(promocion?.disponibilidad ?: false) }
    var promocionProductos by remember { mutableStateOf(promocion?.productos ?: emptyList()) }

    Column(modifier = modifier.fillMaxHeight()) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 16.dp)) {
            Column(modifier = Modifier.fillMaxHeight().weight(2f)) {
                // Draws the editable form for promotions
                PromocionFormContent(modifier = Modifier.weight(3f))

                // Draws the list of products included in the promotion
                PromocionProductsList(
                    productsList = promocionProductos,
                    // TODO Temporal fixed argument, change to pass promocionDicount when it's validated to be of Double type
                    currentPromotionDiscount = ListaDescuentos.random(),
                    modifier = Modifier.weight(2f)
                )
            }

            // List of available products to add to the promotion
            AvailableProductsList(
                modifier = Modifier.weight(1f),
                productoList = ProductoTestList,
                quantitySelectionEnabled = false,
                onAddProductoClick = { producto, _ ->
                    promocionProductos = promocionProductos.addProducto(producto)
                })
        }

        // Bottom buttons to interact with the screen
        Row(modifier = Modifier.fillMaxWidth()) {
            if (editPromocion) {
                BottomButtons(
                    twoButtons = false,
                    firstButtonText = "Borrar",
                    firstButtonAction = onDeleteClick,
                    modifier = Modifier.weight(1f)
                )
            }
            BottomButtons(
                twoButtons = true,
                firstButtonText = if (editPromocion) "Actualizar" else "Agregar",
                firstButtonAction = {
                    onMainButtonClick(
                        Promocion(
                            promocion?.id,
                            promocionDescription,
                            promocionDiscount.toDouble(),
                            promocionAvailability,
                            promocionProductos
                        )
                    )
                },
                secondButtonText = "Limpiar campos",
                secondButtonAction = {
                    promocionDescription = ""
                    promocionDiscount = ""
                    promocionAvailability = false
                    promocionProductos = emptyList()
                },
                firstButtonEnabled = validateFields(
                    promocionDescription,
                    promocionDiscount
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun PromocionFormContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        // TODO Implement form
    }
}

@Composable
private fun PromocionProductsList(
    productsList: List<Producto>,
    currentPromotionDiscount: Double,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxHeight().border(width = Dp.Hairline, color = Color.Gray)) {
            PromocionProductsListHeader()

            Box(modifier = Modifier.weight(1f)) {
                val state = rememberLazyListState()

                LazyColumn(state = state) {
                    items(productsList) {
                        PromocionProductsListItem(it, currentPromotionDiscount)
                        Divider(thickness = Dp.Hairline, color = Color.Gray)
                    }
                }

                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    adapter = ScrollbarAdapter(scrollState = state)
                )
            }
        }
    }
}

@Composable
private fun PromocionProductsListHeader() {
    Surface(elevation = 8.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "id",
                modifier = Modifier.weight(0.5f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Producto",
                modifier = Modifier.weight(1.5f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Precio de venta",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Precio con descuento",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun PromocionProductsListItem(producto: Producto, currentPromotionDiscount: Double) {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "${producto.id}",
            modifier = Modifier.weight(0.5f),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
        Text(
            text = producto.nombre,
            modifier = Modifier.weight(1.5f),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start
        )
        Text(
            text = "${producto.precioVenta}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${producto.precioVenta - (producto.precioVenta * currentPromotionDiscount)}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
    }
}


/*
* Helper methods
*/
private fun validateFields(description: String, discount: String) =
    description != "" && discount != ""

private fun List<Producto>.addProducto(producto: Producto): List<Producto> {
    val newList = this.toMutableList()

    // Validate if is already in the list, if so, update the element content
    if(!this.contains(producto)) {
        newList.add(producto)
    }

    return newList
}

private val ListaDescuentos = listOf(
    0.05, 0.10, 0.15, 0.20, 0.25
)