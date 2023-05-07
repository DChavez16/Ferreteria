package ui.sucursal

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.direccion.Direccion
import model.sucursal.Sucursal
import model.sucursal.SucursalTestList
import model.direccion.municipiosList
import ui.util.BottomButtons
import ui.util.ExpandableDropDownMenu
import ui.util.ScreenHeader
import util.getCustomOutlinedTextFieldColor

@Composable
fun SucursalInfoScreen(
    editable: Boolean,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: (Sucursal) -> Unit,
    onDeleteButtonClick: () -> Unit = {},
    selectedSucursal: Sucursal? = null
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Screen header
        ScreenHeader(
            headerTitle = if (editable) "Editar sucursal" else " Agregar sucursal",
            onReturnButtonClick = onReturnButtonClick
        )

        // Sucursal form
        SucursalForm(
            currentSucursal = selectedSucursal,
            editable = editable,
            onMainButtonClick = onMainButtonClick,
            onDeleteButtonClick = onDeleteButtonClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SucursalForm(
    currentSucursal: Sucursal?,
    editable: Boolean,
    onMainButtonClick: (Sucursal) -> Unit,
    onDeleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Form variables
    var sucursalNombre by remember { mutableStateOf(currentSucursal?.name ?: "") }
    var sucursalDireccion by remember { mutableStateOf(currentSucursal?.contacto?.direccion ?: emptyDireccion()) }
    var sucursalTelefono by remember { mutableStateOf(currentSucursal?.contacto?.telefono ?: "") }

    Column(modifier = modifier.fillMaxHeight()) {
        // Form body
        SucursalFormContent(
            nombre = sucursalNombre,
            onNombreValueChange = { sucursalNombre = it },
            direccion = sucursalDireccion,
            onDireccionValueChange = { sucursalDireccion = it },
            telefono = sucursalTelefono,
            onTelefonoValueChange = { sucursalTelefono = it },
            modifier = Modifier.weight(1f)
        )

        // Bottom buttons to interact with the screen
        Row(modifier = Modifier.fillMaxWidth()) {
            if (editable) {
                BottomButtons(
                    twoButtons = false,
                    firstButtonText = "Borrar",
                    firstButtonAction = onDeleteButtonClick,
                    modifier = Modifier.weight(1f)
                )
            }
            BottomButtons(
                twoButtons = true,
                firstButtonText = if (editable) "Actualizar" else "Agregar",
                firstButtonAction = { onMainButtonClick(SucursalTestList[0]) },
                secondButtonText = "Limpiar campos",
                secondButtonAction = {
                    sucursalNombre = ""
                    sucursalDireccion = emptyDireccion()
                    sucursalTelefono = ""
                },
                firstButtonEnabled = validateCorrectFields(
                    sucursalNombre, sucursalDireccion, sucursalTelefono
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SucursalFormContent(
    nombre: String,
    onNombreValueChange: (String) -> Unit,
    direccion: Direccion,
    onDireccionValueChange: (Direccion) -> Unit,
    telefono: String,
    onTelefonoValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight().fillMaxWidth(0.4f)) {
            // Nombre form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
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

            // Municipio form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Municipio:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                ExpandableDropDownMenu(
                    value = direccion.municipio,
                    optionsList = municipiosList,
                    onValueChange = { onDireccionValueChange(direccion.copy(municipio = it.toString())) },
                    modifier = Modifier.weight(2f)
                )
            }

            // Colonia form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
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

            // Calle form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
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

            // Numero form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
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

            // Codigo Postal form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
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

            // Telefono form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
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
        }
    }
}


/*
Helper methods
*/
private fun validateCorrectFields(nombre: String, direccion: Direccion, telefono: String) =
    nombre.isNotEmpty() && direccion.municipio.isNotEmpty() && validateDireccion(direccion) && telefono.isNotEmpty()

private fun validateDireccion(direccion: Direccion) =
    direccion.colonia.isNotEmpty() && direccion.calle.isNotEmpty() && direccion.numero > 0 && direccion.codigoPostal.isNotEmpty()

private fun emptyDireccion() = Direccion(null, "", "", "", 0, "")