package ui.proveedor

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.*
import ui.util.*
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
    var proveedorDireccion by remember { mutableStateOf(currentProveedor?.contacto?.direccion ?: emptyDireccion()) }
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
                    proveedorDireccion = emptyDireccion()
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
    direccion: Direccion,
    onDireccionValueChange: (Direccion) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().fillMaxHeight().padding(16.dp)
    ) {
        Column {
            // Row of Nombre and Correo form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                // Nombre form field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
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
                Spacer(Modifier.padding(horizontal = 32.dp))

                // Correo form field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
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
            }

            // Row of Telefono and Municipio form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                // Telefono form field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
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
                Spacer(Modifier.padding(horizontal = 32.dp))

                // Municipio form field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Municipio:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                    ExpandableDropDownMenu(
                        value = direccion.municipio,
                        optionsList = municipiosList,
                        onValueChange = { onDireccionValueChange(direccion.copy(municipio = it.toString())) },
                        modifier = Modifier.weight(2f)
                    )
                }
            }

            // Row of Colonia and Calle form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                // Colonia form field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Colonia:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                    OutlinedTextField(
                        value = direccion.colonia,
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        onValueChange = { onDireccionValueChange(direccion.copy(colonia = it)) },
                        singleLine = true,
                        modifier = Modifier.weight(2f)
                    )
                }
                Spacer(Modifier.padding(horizontal = 32.dp))

                // Calle form field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Calle:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                    OutlinedTextField(
                        value = direccion.calle,
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        onValueChange = { onDireccionValueChange(direccion.copy(calle = it)) },
                        singleLine = true,
                        modifier = Modifier.weight(2f)
                    )
                }
            }

            // Row of Numero and Codigo Postal form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                // Numero form field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Numero:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                    OutlinedTextField(
                        value = if (direccion.numero > 0) direccion.numero.toString() else "",
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        onValueChange = {
                            try {
                                onDireccionValueChange(direccion.copy(numero = it.toInt()))
                            } catch (_: Exception) {
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.weight(2f)
                    )
                }
                Spacer(Modifier.padding(horizontal = 32.dp))

                // Codigo Postal form field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Codigo Postal:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                    OutlinedTextField(
                        value = direccion.codigoPostal,
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        onValueChange = { onDireccionValueChange(direccion.copy(codigoPostal = it)) },
                        singleLine = true,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
        }
    }
}


/*
Helper methods
*/
private fun validateCorrectFields(nombre: String, correo: String, telefono: String, direccion: Direccion) =
    nombre.isNotEmpty() && correo.isNotEmpty() && telefono.isNotEmpty() && validateDireccion(direccion)

private fun validateDireccion(direccion: Direccion) =
    direccion.colonia.isEmpty() && direccion.calle.isNotEmpty() && direccion.numero > 0 && direccion.codigoPostal.isNotEmpty()

private fun List<Producto>.addProducto(producto: Producto): List<Producto> {
    val newList = this.toMutableList()

    // Validate if is already in the list, if so, update the element content
    if (!this.contains(producto)) {
        newList.add(producto)
    }

    return newList
}

private fun emptyDireccion() = Direccion(null, "", "", "", 0, "")

