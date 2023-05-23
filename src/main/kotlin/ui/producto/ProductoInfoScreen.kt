package ui.producto

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controller.producto.ProductoController
import ui.util.BottomButtons
import ui.util.ExpandableDropDownMenu
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
        ScreenHeader(
            headerTitle = if (editProduct) "Editar" else "Agregar", onReturnButtonClick = onReturnButtonClick
        )

        /*
        * Form with fields to add or edit a product
        */
        ProductoFormContent(
            nombre = productoState.value.currentProduct.nombre,
            onNombreValueChange = { productoController.updateProductName(it) },
            descripcion = productoState.value.currentProduct.descripcion,
            onDescripcionValueChange = { productoController.updateProductDescription(it) },
            precioVenta = productoState.value.currentProduct.precioVenta,
            onPrecioVentaValueChange = { productoController.updateSellPrice(it) },
            precioReal = productoState.value.currentProduct.precioReal,
            proveedor = productoState.value.currentProduct.proveedor.nombre,
            proveedorList = productoController.proveedorNamePair.map { it.first },
            onProveedorValueChange = { productoController.updateSupplier(it) },
            modifier = Modifier.weight(1f)
        )

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
                firstButtonText = if(editProduct) "Actualizar" else "Agregar",
                firstButtonAction = onMainButtonClick,
                secondButtonText = "Limpiar campos",
                secondButtonAction = { productoController.clearProduct() },
                firstButtonEnabled = productoController.productoIsNotEmpty(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Composable
private fun ProductoFormContent(
    nombre: String,
    onNombreValueChange: (String) -> Unit,
    descripcion: String,
    onDescripcionValueChange: (String) -> Unit,
    precioVenta: Double,
    onPrecioVentaValueChange: (String) -> Unit,
    precioReal: Double,
    proveedor: String,
    proveedorList: List<String>,
    onProveedorValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight().fillMaxWidth(0.4f)) {
            // Product's name form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Nombre:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                OutlinedTextField(
                    value = nombre,
                    onValueChange = onNombreValueChange,
                    textStyle = MaterialTheme.typography.h6,
                    colors = getCustomOutlinedTextFieldColor(),
                    isError = nombre.isEmpty(),
                    singleLine = true,
                    modifier = Modifier.weight(2f)
                )
            }

            // Description form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Descripcion:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = onDescripcionValueChange,
                    textStyle = MaterialTheme.typography.h6,
                    colors = getCustomOutlinedTextFieldColor(),
                    isError = descripcion.isEmpty(),
                    singleLine = false,
                    maxLines = 5,
                    modifier = Modifier.weight(2f)
                )
            }

            // Sell price form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Precio de Venta:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = decimalFormat(precioVenta),
                    onValueChange = onPrecioVentaValueChange,
                    textStyle = MaterialTheme.typography.h6,
                    colors = getCustomOutlinedTextFieldColor(),
                    isError = precioVenta <= 0.0,
                    singleLine = true,
                    modifier = Modifier.weight(2f)
                )
            }

            // Price without IVA field
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Precio sin IVA:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "$ ${decimalFormat(precioReal)}",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(2f)
                )
            }

            // Supplier form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Proveedor:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f)
                )
                ExpandableDropDownMenu(
                    value = proveedor,
                    optionsList = proveedorList,
                    onValueChange = onProveedorValueChange,
                    modifier = Modifier.weight(2f)
                )
            }
        }
    }
}