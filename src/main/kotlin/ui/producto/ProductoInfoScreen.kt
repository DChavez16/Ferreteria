package ui.producto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import controller.producto.ProductoController
import ui.util.BottomButtons
import ui.util.ScreenHeader
import util.decimalFormat
import util.getCustomOutlinedTextFieldColor

@Composable
fun ProductoInfoScreen(
    editProduct: Boolean,
    productoController: ProductoController,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: () -> Unit,
    onDeleteClick: () -> Unit = {}
) {
    val productoState = productoController.productoState.collectAsState()

    Column(modifier = Modifier.fillMaxHeight()) {
        /*
        Header drawing a return button and the title of the screen
         */
        ScreenHeader(headerTitle = if (editProduct) "Editar" else "Agregar", onReturnButtonClick = onReturnButtonClick)

        /*
        Form with fields to add or edit a product
        */
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth().weight(1f)) {
            Image(
                painter = painterResource("icons/producto.png"),
                contentDescription = null,
                modifier = Modifier.weight(2f).padding(16.dp)
            )
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(3f).padding(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
                ) {
                    Text(text = "Nombre:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                    Spacer(Modifier.width(4.dp))
                    EditTextField(
                        value = productoState.value.currentProduct.nombre,
                        onValueChange = { productoController.updateProductName(it) },
                        modifier = Modifier.weight(2f)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
                ) {
                    Text(text = "Descripción:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                    Spacer(Modifier.width(4.dp))
                    EditTextField(
                        value = productoController.productoState.value.currentProduct.descripcion,
                        onValueChange = { productoController.updateProductDescription(it) },
                        singleLine = false,
                        modifier = Modifier.weight(2f)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
                ) {
                    Text(
                        text = "Precio de venta:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(4.dp))
                    EditTextField(
                        value = productoState.value.currentProduct.precioVenta.toString(),
                        onValueChange = { productoController.updateSellPrice(it) },
                        modifier = Modifier.weight(2f)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 20.dp).fillMaxWidth()
                ) {
                    Text(
                        text = "Precio sin IVA: $", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = decimalFormat(productoState.value.currentProduct.precioReal),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
            Spacer(Modifier.weight(2f, fill = false))
        }

        /*
        Buttons to delete product, clear fields or add product
        */
        Row(modifier = Modifier.fillMaxWidth()) {
            if (editProduct) {
                BottomButtons(
                    twoButtons = false,
                    firstButtonText = "Borrar",
                    firstButtonAction = onDeleteClick,
                    modifier = Modifier.weight(1f)
                )
            }
            BottomButtons(
                twoButtons = true,
                firstButtonText = "Modificar",
                firstButtonAction = onMainButtonClick,
                secondButtonText = "Limpiar campos",
                secondButtonAction = { productoController.clearProductFields() },
                firstButtonEnabled = productoController.productoIsNotEmpty(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun EditTextField(
    value: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.h6,
        colors = getCustomOutlinedTextFieldColor(),
        maxLines = 5,
        singleLine = singleLine,
        modifier = modifier
    )
}