package ui.promocion

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.model.Producto
import data.model.ProductoTestList
import data.model.Promocion
import ui.util.AvailableProductsList
import ui.util.BottomButtons
import ui.util.ProductoPromocionDetailsList
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
    var promocionDiscount by remember {
        mutableStateOf(
            if (promocion?.descuento != null) (promocion.descuento * 100).toInt().toString() else ""
        )
    }
    var promocionAvailability by remember { mutableStateOf(promocion?.disponibilidad ?: false) }
    var promocionProductos by remember { mutableStateOf(promocion?.productos ?: emptyList()) }

    Column(modifier = modifier.fillMaxHeight()) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 16.dp)) {
            Column(modifier = Modifier.fillMaxHeight().weight(2f)) {
                // Draws the editable form for promotions
                PromocionFormContent(modifier = Modifier.weight(3f),
                    description = promocionDescription,
                    onDescriptionValueChange = { newValue -> promocionDescription = newValue },
                    descuento = promocionDiscount,
                    onDescuentoValueChange = { newValue ->
                        // Validate if the introduced discount is a valid Double type value
                        try {
                            if (newValue.toInt() <= 100.0 && newValue.toDouble() % 1 == 0.0) {
                                promocionDiscount = newValue.toInt().toString()
                            }
                        } catch (_: NumberFormatException) {
                            promocionDiscount = ""
                        }
                    },
                    disponibilidad = promocionAvailability,
                    onDisponibilidadValueChange = { newValue -> promocionAvailability = newValue })

                // Draws the list of products included in the promotion
                ProductoPromocionDetailsList(
                    productsList = promocionProductos,
                    currentPromotionDiscount = if (promocionDiscount == "") 0 else promocionDiscount.toInt(),
                    modifier = Modifier.weight(2f)
                )
            }

            // List of available products to add to the promotion
            AvailableProductsList(modifier = Modifier.weight(1f),
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
                    promocionDescription, promocionDiscount
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun PromocionFormContent(
    modifier: Modifier = Modifier,
    description: String,
    onDescriptionValueChange: (String) -> Unit,
    descuento: String,
    onDescuentoValueChange: (String) -> Unit,
    disponibilidad: Boolean,
    onDisponibilidadValueChange: (Boolean) -> Unit
) {
    Column(modifier.fillMaxWidth(0.5f), verticalArrangement = Arrangement.Center) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Descripcion:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionValueChange,
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Descuento:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(2f)) {
                OutlinedTextField(
                    value = descuento,
                    onValueChange = onDescuentoValueChange,
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    modifier = Modifier.weight(8f)
                )
                Text(
                    text = " %",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(2f)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Disponible:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            Checkbox(
                checked = disponibilidad,
                onCheckedChange = onDisponibilidadValueChange,
                modifier = Modifier.weight(2f).padding(0.dp)
            )
        }
    }
}


/*
* Helper methods
*/
private fun validateFields(description: String, discount: String) = description != "" && discount != ""

private fun List<Producto>.addProducto(producto: Producto): List<Producto> {
    val newList = this.toMutableList()

    // Validate if is already in the list, if so, update the element content
    if (!this.contains(producto)) {
        newList.add(producto)
    }

    return newList
}