package ui.empleado

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.Empleado
import data.model.EmpleadoTestList
import data.model.SucursalTestList
import ui.util.BottomButtons
import ui.util.ExpandableDropDownMenu
import ui.util.ScreenHeader
import util.UserType
import util.getCustomOutlinedTextFieldColor
import util.getCustomRadioButtonColor

@Composable
fun EmpleadoInfoScreen(
    editable: Boolean,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: (Empleado) -> Unit,
    onDeleteButtonClick: () -> Unit = {},
    selectedEmpleado: Empleado? = null
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Screen header
        ScreenHeader(
            headerTitle = if (editable) "Editar empleado" else "Agregar empleado",
            onReturnButtonClick = onReturnButtonClick
        )
        // Screen form
        EmpleadoForm(
            currentEmpleado = selectedEmpleado,
            editable = editable,
            onMainButtonClick = onMainButtonClick,
            onDeleteButtonClick = onDeleteButtonClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun EmpleadoForm(
    currentEmpleado: Empleado?,
    editable: Boolean,
    onMainButtonClick: (Empleado) -> Unit,
    onDeleteButtonClick: () -> Unit,
    modifier: Modifier
) {
    // Form variables
    var empleadoNombre by remember { mutableStateOf(currentEmpleado?.nombre ?: "") }
    var empleadoCorreo by remember { mutableStateOf(currentEmpleado?.contacto?.correo ?: "") }
    var empleadoTelefono by remember { mutableStateOf(currentEmpleado?.contacto?.telefono ?: "") }
    var empleadoSucursal by remember { mutableStateOf(currentEmpleado?.sucursal?.name ?: "") }
    var empleadoAdmin by remember { mutableStateOf(currentEmpleado?.puesto == UserType.ADMINISTRATOR) }

    Column(modifier = modifier.fillMaxHeight()) {
        // Form content
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f).padding(16.dp).fillMaxWidth()
        ) {
            EmpleadoFormContent(
                nombre = empleadoNombre, onNombreValueChange = { empleadoNombre = it },
                correo = empleadoCorreo, onCorreoValueChange = { empleadoCorreo = it },
                telefono = empleadoTelefono, onTelefonoValueChange = { empleadoTelefono = it },
                sucursal = empleadoSucursal, onSucursalValueChange = { empleadoSucursal = it },
                isAdmin = empleadoAdmin, onIsAdminValueChange = { empleadoAdmin = it },
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
                firstButtonAction = { onMainButtonClick(EmpleadoTestList[0]) },
                secondButtonText = "Limpiar campos",
                secondButtonAction = {
                    empleadoNombre = ""
                    empleadoCorreo = ""
                    empleadoTelefono = ""
                    empleadoSucursal = ""
                    empleadoAdmin = false
                },
                firstButtonEnabled = validateCorrectFields(
                    empleadoNombre,
                    empleadoCorreo,
                    empleadoTelefono,
                    empleadoSucursal
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun EmpleadoFormContent(
    nombre: String,
    onNombreValueChange: (String) -> Unit,
    correo: String,
    onCorreoValueChange: (String) -> Unit,
    telefono: String,
    onTelefonoValueChange: (String) -> Unit,
    sucursal: String,
    onSucursalValueChange: (String) -> Unit,
    isAdmin: Boolean,
    onIsAdminValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val sucursalOptions = getSucursalOptions()

    Column(verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxHeight()) {
        // Nombre form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
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
            Spacer(Modifier.padding(horizontal = 32.dp))
            Spacer(Modifier.weight(1f))
        }

        // Row of Correo and Telefono form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
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
                    singleLine = true,
                    modifier = Modifier.weight(2f)
                )
            }
        }

        // Row of Sucursal and Puesto form field
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            // Sucursal form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Municipio:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                ExpandableDropDownMenu(
                    value = sucursal,
                    optionsList = sucursalOptions,
                    onValueChange = { onSucursalValueChange(it.toString()) },
                    modifier = Modifier.weight(2f)
                )
            }
            Spacer(Modifier.padding(horizontal = 32.dp))

            // Column of Puesto form field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f).height(55.dp)) {
                    Text(text = "Puesto:", style = MaterialTheme.typography.h6)
                }
                // Column of Puesto's radio button options
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = !isAdmin,
                            onClick = { onIsAdminValueChange(false) },
                            colors = getCustomRadioButtonColor()
                        )
                        Text("Cajero", style = MaterialTheme.typography.h6)
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
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


/*
Helper methods
*/
private fun validateCorrectFields(nombre: String, correo: String, telefono: String, sucursal: String) =
    nombre.isNotEmpty() && correo.isNotEmpty() && telefono.isNotEmpty() && sucursal.isNotEmpty()

private fun getSucursalOptions(): List<String> {
    val newList = mutableListOf<String>()

    SucursalTestList.forEach { sucursal ->
        newList.add(sucursal.name)
    }

    return newList
}