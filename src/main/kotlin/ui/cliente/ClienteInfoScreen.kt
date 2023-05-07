package ui.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.cliente.Cliente
import model.cliente.ClienteTestList
import ui.util.BottomButtons
import ui.util.ScreenHeader
import util.getCustomCheckboxColor
import util.getCustomOutlinedTextFieldColor

@Composable
fun ClienteInfoScreen(
    editable: Boolean,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: (Cliente) -> Unit,
    onDeleteButtonClick: () -> Unit = {},
    selectedCliente: Cliente? = null
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Screen header
        ScreenHeader(
            headerTitle = if (editable) "Editar cliente" else "Agregar cliente",
            onReturnButtonClick = onReturnButtonClick
        )
        // Screen form
        ClienteForm(
            currentCliente = selectedCliente,
            editable = editable,
            onMainButtonClick = onMainButtonClick,
            onDeleteButtonClick = onDeleteButtonClick
        )
    }
}

@Composable
private fun ClienteForm(
    currentCliente: Cliente?,
    editable: Boolean,
    onMainButtonClick: (Cliente) -> Unit,
    onDeleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Form variables
    var clienteNombre by remember { mutableStateOf(currentCliente?.nombre ?: "") }
    var clienteCorreo by remember { mutableStateOf(currentCliente?.contacto?.correo ?: "") }
    var clienteTelefono by remember { mutableStateOf(currentCliente?.contacto?.telefono ?: "") }
    var clienteSuscripcion by remember { mutableStateOf(currentCliente?.suscrito ?: false) }

    Column(modifier = modifier.fillMaxHeight()) {
        // Form content
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.weight(1f).padding(16.dp).fillMaxWidth()) {
            ClienteFormContent(
                nombre = clienteNombre, onNombreValueChange = { clienteNombre = it },
                correo = clienteCorreo, onCorreoValueChange = { clienteCorreo = it },
                telefono = clienteTelefono, onTelefonoValueChange = { clienteTelefono = it },
                sucripcion = clienteSuscripcion, onSuscripcionValueChange = { clienteSuscripcion = it },
                modifier = Modifier.fillMaxWidth(0.4f)
            )
        }

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
                firstButtonAction = { onMainButtonClick(ClienteTestList[0]) },
                secondButtonText = "Limpiar campos",
                secondButtonAction = {
                    clienteNombre = ""
                    clienteCorreo = ""
                    clienteTelefono = ""
                    clienteSuscripcion = false
                },
                firstButtonEnabled = validateCorrectFields(
                    clienteNombre, clienteCorreo, clienteTelefono
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ClienteFormContent(
    nombre: String,
    onNombreValueChange: (String) -> Unit,
    correo: String,
    onCorreoValueChange: (String) -> Unit,
    telefono: String,
    onTelefonoValueChange: (String) -> Unit,
    sucripcion: Boolean,
    onSuscripcionValueChange: (Boolean) -> Unit,
    modifier: Modifier
) {
    Column(verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxHeight()) {
        // Nombre form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Nombre:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = nombre,
                onValueChange = onNombreValueChange,
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
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
                onValueChange = onCorreoValueChange,
                placeholder = { Text("example@email.com") },
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
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
                onValueChange = onTelefonoValueChange,
                placeholder = { Text("00 0000 0000") },
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }

        // Suscrito form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Suscripción:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            Checkbox(
                checked = sucripcion,
                onCheckedChange = onSuscripcionValueChange,
                colors = getCustomCheckboxColor(),
                modifier = Modifier.weight(2f)
            )
        }
    }
}


/*
Helper methods
*/
private fun validateCorrectFields(nombre: String, correo: String, telefono: String) =
    nombre.isNotEmpty() && correo.isNotEmpty() && telefono.isNotEmpty()