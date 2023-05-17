package ui.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controller.cliente.ClienteController
import ui.util.BottomButtons
import ui.util.ScreenHeader
import util.*

@Composable
fun ClienteInfoScreen(
    editable: Boolean,
    clienteController: ClienteController,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Screen header
        ScreenHeader(
            headerTitle = if (editable) "Editar cliente" else "Agregar cliente",
            onReturnButtonClick = onReturnButtonClick
        )
        // Screen form
        ClienteForm(
            clienteController = clienteController,
            editable = editable,
            onMainButtonClick = onMainButtonClick,
            onDeleteButtonClick = onDeleteButtonClick
        )
    }
}

@Composable
private fun ClienteForm(
    clienteController: ClienteController,
    editable: Boolean,
    onMainButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clienteState = clienteController.clienteState.collectAsState()

    Column(modifier = modifier.fillMaxHeight()) {
        // Form content
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.weight(1f).padding(16.dp).fillMaxWidth()) {
            ClienteFormContent(
                primerNombre = clienteState.value.currentCliente.primerNombre,
                onPrimerNombreValueChange = { clienteController.updateClientFirstName(it) },
                segundoNombre = clienteState.value.currentCliente.segundoNombre,
                onSegundoNombreValueChange = { clienteController.updateClientSecondName(it) },
                apellido = clienteState.value.currentCliente.apellido,
                onApellidoValueChange = { clienteController.updateClientLastName(it) },
                correo = clienteState.value.currentCliente.contacto.correo,
                onCorreoValueChange = { clienteController.updateClientEmail(it) },
                telefono = clienteState.value.currentCliente.contacto.telefono,
                onTelefonoValueChange = { clienteController.updateClientPhone(it) },
                sucripcion = clienteState.value.currentCliente.suscrito,
                onSuscripcionValueChange = { clienteController.updateClientSuscription(it) },
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
                firstButtonAction = onMainButtonClick,
                secondButtonText = "Limpiar campos",
                secondButtonAction = { clienteController.clearCliente() },
                firstButtonEnabled = clienteController.clienteIsNotEmpty(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ClienteFormContent(
    primerNombre: String,
    onPrimerNombreValueChange: (String) -> Unit,
    segundoNombre: String,
    onSegundoNombreValueChange: (String) -> Unit,
    apellido: String,
    onApellidoValueChange: (String) -> Unit,
    correo: String,
    onCorreoValueChange: (String) -> Unit,
    telefono: String,
    onTelefonoValueChange: (String) -> Unit,
    sucripcion: Boolean,
    onSuscripcionValueChange: (Boolean) -> Unit,
    modifier: Modifier
) {
    Column(verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxHeight()) {
        // Primer nombre form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Primer nombre:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = primerNombre,
                onValueChange = onPrimerNombreValueChange,
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
                isError = !primerNombre.isValidPersonName(),
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }

        // Segundo nombre form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Segundo nombre:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = segundoNombre,
                onValueChange = onSegundoNombreValueChange,
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
                isError = !segundoNombre.isValidPersonName(),
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }

        // Apellido form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Apellido(s):", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = apellido,
                onValueChange = onApellidoValueChange,
                textStyle = MaterialTheme.typography.h6,
                colors = getCustomOutlinedTextFieldColor(),
                isError = !apellido.isValidPersonName(),
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
                isError = !correo.isValidEmail(),
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
                isError = !telefono.isValidPhoneNumber(),
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