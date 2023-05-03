package ui.proveedor

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.Producto
import data.model.ProductoTestList
import data.model.Proveedor
import data.model.ProveedorTestList
import ui.util.AvailableProductsList
import ui.util.BottomButtons
import ui.util.ProductosProveedorDetailsList
import ui.util.ScreenHeader
import util.getCustomOutlinedTextFieldColor

@Composable
fun ProveedorInfoScreen(
    editable: Boolean,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: (Proveedor) -> Unit,
    onDeleteClick: () -> Unit = {},
    selectedProveedor: Proveedor? = null
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Screen header
        ScreenHeader(
            headerTitle = if (editable) "Editar proveedor" else "Agregar proveedor",
            onReturnButtonClick = onReturnButtonClick
        )
        // Screen form
        ProveedorForm(
            currentProveedor = selectedProveedor,
            editable = editable,
            onMainButtonClick = onMainButtonClick,
            onDeleteClick = onDeleteClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ProveedorForm(
    currentProveedor: Proveedor?,
    editable: Boolean,
    onMainButtonClick: (Proveedor) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier
) {
    // Form variables
    var proveedorNombre by remember { mutableStateOf(currentProveedor?.nombre ?: "") }
    var proveedorCorreo by remember { mutableStateOf(currentProveedor?.contacto?.correo ?: "") }
    var proveedorTelefono by remember { mutableStateOf(currentProveedor?.contacto?.telefono ?: "") }
    var proveedorDireccion by remember { mutableStateOf(currentProveedor?.contacto?.direccion ?: "") }
    var proveedorProductos by remember { mutableStateOf(currentProveedor?.productos ?: emptyList()) }

    Column(modifier = modifier.fillMaxHeight()) {
        // Form body
        Row(modifier = Modifier.fillMaxHeight().weight(1f).padding(horizontal = 16.dp)) {
            // Form content and supplier's privided products list
            Column(modifier = Modifier.fillMaxHeight().weight(2f)) {
                // Draws the editable form for suppliers
                ProveedorFormContent(
                    nombre = proveedorNombre,
                    onNombreValueChange = { newNombre -> proveedorNombre = newNombre },
                    correo = proveedorCorreo,
                    onCorreoValueChange = { newCorreo -> proveedorCorreo = newCorreo },
                    telefono = proveedorTelefono,
                    onTelefonoValueChange = { newTelefono -> proveedorTelefono = newTelefono },
                    direccion = proveedorDireccion,
                    onDireccionValueChange = { newDireccion -> proveedorDireccion = newDireccion },
                    modifier = Modifier.weight(3f)
                )

                // Draws the list of products provided by the supplier
                ProductosProveedorDetailsList(
                    productosList = proveedorProductos, modifier = Modifier.weight(2f)
                )
            }

            // List of available products to add to the supplier
            AvailableProductsList(modifier = Modifier.weight(1f),
                productoList = ProductoTestList,
                quantitySelectionEnabled = false,
                onAddProductoClick = { producto, _ ->
                    proveedorProductos = proveedorProductos.addProducto(producto)
                })
        }

        // Bottom buttons to interact with the screen
        Row(modifier = Modifier.fillMaxWidth()) {
            if (editable) {
                BottomButtons(
                    twoButtons = false,
                    firstButtonText = "Borrar",
                    firstButtonAction = onDeleteClick,
                    modifier = Modifier.weight(1f)
                )
            }
            BottomButtons(
                twoButtons = true,
                firstButtonText = if (editable) "Actualizar" else "Agregar",
                firstButtonAction = { onMainButtonClick(ProveedorTestList[0]) },
                secondButtonText = "Limpiar campos",
                secondButtonAction = {
                    proveedorNombre = ""
                    proveedorCorreo = ""
                    proveedorTelefono = ""
                    proveedorDireccion = ""
                    proveedorProductos = emptyList()
                },
                firstButtonEnabled = validateCorrectFields(
                    proveedorNombre, proveedorCorreo, proveedorTelefono, proveedorDireccion
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ProveedorFormContent(
    nombre: String,
    onNombreValueChange: (String) -> Unit,
    correo: String,
    onCorreoValueChange: (String) -> Unit,
    telefono: String,
    onTelefonoValueChange: (String) -> Unit,
    direccion: String,
    onDireccionValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth(0.5f), verticalArrangement = Arrangement.Center) {
        // Nombre form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Nombre:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = nombre,
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
                onValueChange = onNombreValueChange,
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }

        // Correo form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Correo:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = correo,
                placeholder = { Text("example@email.com") },
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
                onValueChange = onCorreoValueChange,
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }

        // Telefono form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Teléfono:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = telefono,
                placeholder = { Text("00 0000 0000") },
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
                onValueChange = onTelefonoValueChange,
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }

        // Direccion form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Dirección:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = direccion,
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
                onValueChange = onDireccionValueChange,
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }
    }
}


/*
Helper methods
*/
private fun validateCorrectFields(nombre: String, correo: String, telefono: String, direccion: String) =
    nombre.isNotEmpty() && correo.isNotEmpty() && telefono.isNotEmpty() && direccion.isNotEmpty()

private fun List<Producto>.addProducto(producto: Producto): List<Producto> {
    val newList = this.toMutableList()

    // Validate if is already in the list, if so, update the element content
    if (!this.contains(producto)) {
        newList.add(producto)
    }

    return newList
}