package ui.promocion

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import controller.promocion.PromocionController
import ui.util.AvailableProductsList
import ui.util.BottomButtons
import ui.util.ProductoPromocionDetailsList
import ui.util.ScreenHeader
import util.getCustomCheckboxColor
import util.getCustomOutlinedTextFieldColor

@Composable
fun PromocionInfoScreen(
    editPromocion: Boolean,
    promocionController: PromocionController,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: () -> Unit,
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
            promocionController = promocionController,
            onMainButtonClick = onMainButtonClick,
            onDeleteClick = onDeleteClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun PromocionForm(
    editPromocion: Boolean,
    promocionController: PromocionController,
    onMainButtonClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val promocionState = promocionController.promocionState.collectAsState()

    Column(modifier = modifier.fillMaxHeight()) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 16.dp)) {
            Column(modifier = Modifier.fillMaxHeight().weight(2f)) {
                // Draws the editable form for promotions
                PromocionFormContent(modifier = Modifier.weight(3f),
                    description = promocionState.value.currentPromocion.description,
                    onDescriptionValueChange = { promocionController.updatePromotionDescription(it) },
                    descuento = promocionState.value.currentPromocion.descuento.times(100).toInt().toString(),
                    onDescuentoValueChange = { promocionController.updatePromotionDiscount(it) },
                    disponibilidad = promocionState.value.currentPromocion.disponibilidad,
                    onDisponibilidadValueChange = { promocionController.updatePromotionAvailability(it) })

                // Draws the list of products included in the promotion
                ProductoPromocionDetailsList(
                    productsList = promocionState.value.currentPromocion.productos,
                    currentPromotionDiscount = promocionState.value.currentPromocion.descuento,
                    modifier = Modifier.weight(2f),
                    editable = true,
                    onRemoveButtonClick = { promocionController.removeProductFromPromotion(it) }
                )
            }

            // List of available products to add to the promotion
            AvailableProductsList(modifier = Modifier.weight(1f),
                productoList = promocionState.value.filteredProductoList,
                quantitySelectionEnabled = false,
                onAddProductoClick = { producto, _ -> promocionController.addProductToPromotion(producto) })
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
                firstButtonAction = onMainButtonClick,
                secondButtonText = "Limpiar campos",
                secondButtonAction = { promocionController.clearPromocion() },
                firstButtonEnabled = promocionController.promocionIsNotEmpty(),
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
            Text(text = "Descripción:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = description,
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
                onValueChange = onDescriptionValueChange,
                isError = description.isEmpty(),
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
                    value = if(descuento == "0") "" else descuento,
                    onValueChange = onDescuentoValueChange,
                    textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                    colors = getCustomOutlinedTextFieldColor(),
                    isError = descuento == "0",
                    singleLine = true,
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
                colors = getCustomCheckboxColor(),
                modifier = Modifier.weight(2f).padding(0.dp)
            )
        }
    }
}