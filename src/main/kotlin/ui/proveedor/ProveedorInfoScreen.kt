package ui.proveedor

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controller.proveedor.ProveedorController
import model.direccion.municipiosList
import model.producto.ProductoTestList
import ui.util.*
import util.getCustomOutlinedTextFieldColor

@Composable
fun ProveedorInfoScreen(
    editable: Boolean,
    proveedorController: ProveedorController,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: () -> Unit,
    onDeleteClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Screen header
        ScreenHeader(
            headerTitle = if (editable) "Editar proveedor" else "Agregar proveedor",
            onReturnButtonClick = onReturnButtonClick
        )
        // Screen form
        ProveedorForm(
            proveedorController = proveedorController,
            editable = editable,
            onMainButtonClick = onMainButtonClick,
            onDeleteClick = onDeleteClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ProveedorForm(
    proveedorController: ProveedorController,
    editable: Boolean,
    onMainButtonClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier
) {
    val proveedorState = proveedorController.proveedorState.collectAsState()

    Column(modifier = modifier.fillMaxHeight()) {
        // Form body
        Row(modifier = Modifier.fillMaxHeight().weight(1f).padding(horizontal = 16.dp)) {
            // Form content and supplier's privided products list
            Column(modifier = Modifier.fillMaxHeight().weight(2f)) {
                // Draws the editable form for suppliers
                ProveedorFormContent(
                    nombre = proveedorState.value.currentProveedor.nombre,
                    onNombreValueChange = { proveedorController.updateSupplierName(it) },
                    correo = proveedorState.value.currentProveedor.contacto.correo,
                    onCorreoValueChange = { proveedorController.updateSupplierMail(it) },
                    telefono = proveedorState.value.currentProveedor.contacto.telefono,
                    onTelefonoValueChange = { proveedorController.updateSupplierPhone(it) },
                    municipio = proveedorState.value.currentProveedor.contacto.direccion.municipio,
                    onMunicipioValueChange = { proveedorController.updateSupplierTown(it) },
                    colonia = proveedorState.value.currentProveedor.contacto.direccion.colonia,
                    onColoniaValueChange = { proveedorController.updateSupplierNeighborhood(it) },
                    calle = proveedorState.value.currentProveedor.contacto.direccion.calle,
                    onCalleValueChange = { proveedorController.updateSupplierStreet(it) },
                    numero = proveedorState.value.currentProveedor.contacto.direccion.numero,
                    onNumeroValueChange = { proveedorController.updateSupplierDirectionNumber(it) },
                    codigoPostal = proveedorState.value.currentProveedor.contacto.direccion.codigoPostal,
                    onCodigoPostalValueChange = { proveedorController.updateSupplierPostalCode(it) },
                    modifier = Modifier.weight(3f)
                )

                // Draws the list of products provided by the supplier
                ProductosProveedorDetailsList(
                    productosList = proveedorState.value.currentProveedor.productos, modifier = Modifier.weight(2f)
                )
            }

            // List of available products to add to the supplier
            AvailableProductsList(modifier = Modifier.weight(1f),
                productoList = ProductoTestList,
                quantitySelectionEnabled = false,
                onAddProductoClick = { producto, _ -> proveedorController.addProductToPromotion(producto) })
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
                firstButtonAction = onMainButtonClick,
                secondButtonText = "Limpiar campos",
                secondButtonAction = { proveedorController.clearProveedor() },
                firstButtonEnabled = proveedorController.proveedorIsNotEmpty(),
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
    municipio: String,
    onMunicipioValueChange: (String) -> Unit,
    colonia: String,
    onColoniaValueChange: (String) -> Unit,
    calle: String,
    onCalleValueChange: (String) -> Unit,
    numero: Int,
    onNumeroValueChange: (String) -> Unit,
    codigoPostal: String,
    onCodigoPostalValueChange: (String) -> Unit,
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
                        value = municipio,
                        optionsList = municipiosList,
                        onValueChange = { onMunicipioValueChange(it.toString()) },
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
                        value = colonia,
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        onValueChange = onColoniaValueChange,
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
                        value = calle,
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        onValueChange = onCalleValueChange,
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
                        value = if (numero > 0) numero.toString() else "",
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        onValueChange = onNumeroValueChange,
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
                        value = codigoPostal,
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        onValueChange = onCodigoPostalValueChange,
                        singleLine = true,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
        }
    }
}