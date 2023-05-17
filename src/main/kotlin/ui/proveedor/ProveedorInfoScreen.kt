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
import ui.util.BottomButtons
import ui.util.ExpandableDropDownMenu
import ui.util.ScreenHeader
import util.getCustomOutlinedTextFieldColor
import util.isValidEmail
import util.isValidPhoneNumber
import util.isValidPostalCode

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
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier.weight(1f).padding(16.dp).fillMaxWidth()
        ) {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth(0.8f).fillMaxHeight()) {
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
                            value = proveedorState.value.currentProveedor.nombre,
                            textStyle = MaterialTheme.typography.h6,
                            colors = getCustomOutlinedTextFieldColor(),
                            onValueChange = { proveedorController.updateSupplierName(it) },
                            isError = proveedorState.value.currentProveedor.nombre.isEmpty(),
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
                            value = proveedorState.value.currentProveedor.contacto.correo,
                            placeholder = { Text("example@email.com") },
                            textStyle = MaterialTheme.typography.h6,
                            colors = getCustomOutlinedTextFieldColor(),
                            onValueChange = { proveedorController.updateSupplierMail(it) },
                            isError = !proveedorState.value.currentProveedor.contacto.correo.isValidEmail(),
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
                            value = proveedorState.value.currentProveedor.contacto.telefono,
                            placeholder = { Text("00 0000 0000") },
                            textStyle = MaterialTheme.typography.h6,
                            colors = getCustomOutlinedTextFieldColor(),
                            onValueChange = { proveedorController.updateSupplierPhone(it) },
                            isError = !proveedorState.value.currentProveedor.contacto.telefono.isValidPhoneNumber(),
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
                            value = proveedorState.value.currentProveedor.contacto.direccion.municipio,
                            optionsList = municipiosList,
                            onValueChange = { proveedorController.updateSupplierTown(it) },
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
                            value = proveedorState.value.currentProveedor.contacto.direccion.colonia,
                            textStyle = MaterialTheme.typography.h6,
                            colors = getCustomOutlinedTextFieldColor(),
                            onValueChange = { proveedorController.updateSupplierNeighborhood(it) },
                            isError = proveedorState.value.currentProveedor.contacto.direccion.colonia.isEmpty(),
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
                            value = proveedorState.value.currentProveedor.contacto.direccion.calle,
                            textStyle = MaterialTheme.typography.h6,
                            colors = getCustomOutlinedTextFieldColor(),
                            onValueChange = { proveedorController.updateSupplierStreet(it) },
                            isError = proveedorState.value.currentProveedor.contacto.direccion.calle.isEmpty(),
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
                        Text(text = "Número:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                        OutlinedTextField(
                            value = with(proveedorState.value.currentProveedor.contacto.direccion.numero) { if (this > 0) this.toString() else "" },
                            textStyle = MaterialTheme.typography.h6,
                            colors = getCustomOutlinedTextFieldColor(),
                            onValueChange = { proveedorController.updateSupplierDirectionNumber(it) },
                            isError = proveedorState.value.currentProveedor.contacto.direccion.numero <= 0,
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
                        Text(
                            text = "Código Postal:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = proveedorState.value.currentProveedor.contacto.direccion.codigoPostal,
                            textStyle = MaterialTheme.typography.h6,
                            colors = getCustomOutlinedTextFieldColor(),
                            onValueChange = { proveedorController.updateSupplierPostalCode(it) },
                            isError = !proveedorState.value.currentProveedor.contacto.direccion.codigoPostal.isValidPostalCode(),
                            singleLine = true,
                            modifier = Modifier.weight(2f)
                        )
                    }
                }
            }
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