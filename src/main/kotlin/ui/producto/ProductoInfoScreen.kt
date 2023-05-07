package ui.producto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.producto.Producto
import ui.util.BottomButtons
import ui.util.ScreenHeader
import util.decimalFormat
import util.getCustomOutlinedTextFieldColor

@Composable
fun ProductoInfoScreen(
    editProduct: Boolean,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: () -> Unit,
    producto: Producto? = null,
    onDeleteClick: () -> Unit = {}
) {
    var currentProductoName by remember { mutableStateOf(producto?.nombre ?: "") }
    var currentProductoDescription by remember { mutableStateOf(producto?.descripcion ?: "") }
    var currentProductoSellPrice by remember { mutableStateOf(if (producto?.precioVenta == null) "" else producto.precioVenta.toString()) }

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
                        value = currentProductoName,
                        onValueChange = { currentProductoName = it },
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
                        value = currentProductoDescription,
                        onValueChange = { currentProductoDescription = it },
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
                        value = currentProductoSellPrice, onValueChange = {
                            try {
                                if (it.toDouble() <= 100000.0) {
                                    currentProductoSellPrice = if (it == "") "" else it.toDouble().toString()
                                }
                            } catch (_: NumberFormatException) {
                            }
                        }, modifier = Modifier.weight(2f)
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
                        text = decimalFormat(
                            getPrecioSinIVA(
                                if (currentProductoSellPrice == "") 0.0
                                else currentProductoSellPrice.toDouble()
                            )
                        ), style = MaterialTheme.typography.h6, modifier = Modifier.weight(2f)
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
                secondButtonAction = {
                    currentProductoName = ""
                    currentProductoDescription = ""
                    currentProductoSellPrice = ""
                },
                firstButtonEnabled = validateFields(
                    currentProductoName,
                    currentProductoDescription,
                    if (currentProductoSellPrice == "") 0.0 else currentProductoSellPrice.toDouble()
                ),
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


private fun validateFields(name: String, description: String, sellPrice: Double) =
    name != "" && description != "" && sellPrice != 0.0

private fun getPrecioSinIVA(precioConIVA: Double) = precioConIVA / 1.16