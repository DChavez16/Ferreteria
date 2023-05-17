package ui.empleado

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controller.empleado.EmpleadoController
import model.sucursal.Sucursal
import ui.util.BottomButtons
import ui.util.ExpandableDropDownMenu
import ui.util.ScreenHeader
import util.*

@Composable
fun EmpleadoInfoScreen(
    editable: Boolean,
    empleadoController: EmpleadoController,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Screen header
        ScreenHeader(
            headerTitle = if (editable) "Editar empleado" else "Agregar empleado",
            onReturnButtonClick = onReturnButtonClick
        )
        // Screen form
        EmpleadoForm(
            editable = editable,
            empleadoController = empleadoController,
            onMainButtonClick = onMainButtonClick,
            onDeleteButtonClick = onDeleteButtonClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun EmpleadoForm(
    editable: Boolean,
    empleadoController: EmpleadoController,
    onMainButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    modifier: Modifier
) {
    val empleadoState = empleadoController.empleadoState.collectAsState()

    Column(modifier = modifier.fillMaxHeight()) {
        // Form content
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier.weight(1f).padding(16.dp).fillMaxWidth()
        ) {
            EmpleadoFormContent(
                primerNombre = empleadoState.value.currentEmpleado.primerNombre,
                onPrimerNombreValueChange = { empleadoController.updateClientFirstName(it) },
                segundoNombre = empleadoState.value.currentEmpleado.segundoNombre,
                onSegundoNombreValueChange = { empleadoController.updateClientSecondName(it) },
                apellido = empleadoState.value.currentEmpleado.apellido,
                onApellidoValueChange = { empleadoController.updateClientLastName(it) },
                correo = empleadoState.value.currentEmpleado.contacto.correo,
                onCorreoValueChange = { empleadoController.updateEmployeeEmail(it) },
                telefono = empleadoState.value.currentEmpleado.contacto.telefono,
                onTelefonoValueChange = { empleadoController.updateEmployeePhone(it) },
                sucursal = empleadoState.value.currentEmpleado.sucursal.name,
                sucursalList = empleadoController.sucursalNamePair,
                onSucursalValueChange = { empleadoController.updateEmployeeBranch(it) },
                isAdmin = empleadoState.value.currentEmpleado.puesto == UserType.ADMINISTRATOR,
                onIsAdminValueChange = { empleadoController.updateEmployeePosition(it) },
                modifier = Modifier.fillMaxWidth(0.8f)
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
                secondButtonAction = { empleadoController.clearEmpleado() },
                firstButtonEnabled = empleadoController.empleadoIsNotEmpty(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun EmpleadoFormContent(
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
    sucursal: String,
    sucursalList: MutableList<Pair<String, Sucursal>>,
    onSucursalValueChange: (String) -> Unit,
    isAdmin: Boolean,
    onIsAdminValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxHeight()) {
        // Primer nombre form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
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
            Spacer(Modifier.padding(horizontal = 32.dp))
            Spacer(Modifier.weight(1f))
        }

        // Segundo nombre form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
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
            Spacer(Modifier.padding(horizontal = 32.dp))
            Spacer(Modifier.weight(1f))
        }

        // Apellido form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
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
            Spacer(Modifier.padding(horizontal = 32.dp))
            Spacer(Modifier.weight(1f))
        }

        // Row of Correo and Telefono form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            // Correo form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
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
            Spacer(Modifier.padding(horizontal = 32.dp))

            // Telefono form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
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
        }

        // Row of Sucursal and Puesto form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            // Sucursal form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Sucursal:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                ExpandableDropDownMenu(
                    value = sucursal,
                    optionsList = sucursalList.map { it.first },
                    onValueChange = { onSucursalValueChange(it) },
                    modifier = Modifier.weight(2f)
                )
            }
            Spacer(Modifier.padding(horizontal = 32.dp))

            // Column of Puesto form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f).height(55.dp)) {
                    Text(text = "Puesto:", style = MaterialTheme.typography.h6)
                }
                // Column of Puesto's radio button options
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = !isAdmin,
                            onClick = { onIsAdminValueChange(false) },
                            colors = getCustomRadioButtonColor()
                        )
                        Text("Cajero", style = MaterialTheme.typography.h6)
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isAdmin,
                            onClick = { onIsAdminValueChange(true) },
                            colors = getCustomRadioButtonColor()
                        )
                        Text("Administrador", style = MaterialTheme.typography.h6)
                    }
                }
            }
        }
    }
}