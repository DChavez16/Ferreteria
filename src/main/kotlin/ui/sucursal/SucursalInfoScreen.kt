package ui.sucursal

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.Sucursal
import data.model.SucursalTestList
import ui.util.BottomButtons
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
    var sucursalDireccion by remember { mutableStateOf(currentSucursal?.contacto?.direccion ?: "") }
    var sucursalTelefono by remember { mutableStateOf(currentSucursal?.contacto?.telefono ?: "") }

    Column(modifier = modifier.fillMaxHeight()) {
        // Form body
        SucursalFormContent(
            nombre = sucursalNombre, onNombreValueChange = { sucursalNombre = it },
            direccion = sucursalDireccion, onDireccionValueChange = { sucursalDireccion = it },
            telefono = sucursalTelefono, onTelefonoValueChange = { sucursalTelefono = it },
            modifier = Modifier.weight(1f)
        )

        // Bottom button sto interact with the screen
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
                    sucursalDireccion = ""
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
    direccion: String,
    onDireccionValueChange: (String) -> Unit,
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

            // Direccion form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
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

            // Correo form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
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
private fun validateCorrectFields(nombre: String, direccion: String, telefono: String) =
    nombre.isNotEmpty() && direccion.isNotEmpty() && telefono.isNotEmpty()