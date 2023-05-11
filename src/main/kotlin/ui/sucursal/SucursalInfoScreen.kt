package ui.sucursal

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controller.sucursal.SucursalController
import model.direccion.municipiosList
import ui.util.BottomButtons
import ui.util.ExpandableDropDownMenu
import ui.util.ScreenHeader
import util.getCustomOutlinedTextFieldColor

@Composable
fun SucursalInfoScreen(
    editable: Boolean,
    sucursalController: SucursalController,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Screen header
        ScreenHeader(
            headerTitle = if (editable) "Editar sucursal" else " Agregar sucursal",
            onReturnButtonClick = onReturnButtonClick
        )

        // Sucursal form
        SucursalForm(
            sucursalController = sucursalController,
            editable = editable,
            onMainButtonClick = onMainButtonClick,
            onDeleteButtonClick = onDeleteButtonClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SucursalForm(
    sucursalController: SucursalController,
    editable: Boolean,
    onMainButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sucursalState = sucursalController.sucursalState.collectAsState()

    Column(modifier = modifier.fillMaxHeight()) {
        // Form body
        SucursalFormContent(
            nombre = sucursalState.value.currentSucursal.name,
            onNombreValueChange = { sucursalController.updateBranchName(it) },
            telefono = sucursalState.value.currentSucursal.contacto.telefono,
            onTelefonoValueChange = { sucursalController.updateBranchPhone(it) },
            municipio = sucursalState.value.currentSucursal.contacto.direccion.municipio,
            onMunicipioValueChange = { sucursalController.updateBranchTown(it) },
            colonia = sucursalState.value.currentSucursal.contacto.direccion.colonia,
            onColoniaValueChange = { sucursalController.updateBranchNeighborhood(it) },
            calle = sucursalState.value.currentSucursal.contacto.direccion.calle,
            onCalleValueChange = { sucursalController.updateBranchStreet(it) },
            numero = sucursalState.value.currentSucursal.contacto.direccion.numero,
            onNumeroValueChange = { sucursalController.updateBranchDirectionNumber(it) },
            codigoPostal = sucursalState.value.currentSucursal.contacto.direccion.codigoPostal,
            onCodigoPostalValueChange = { sucursalController.updateBranchPostalCode(it) },
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
                firstButtonAction = onMainButtonClick,
                secondButtonText = "Limpiar campos",
                secondButtonAction = { sucursalController.clearSucursal() },
                firstButtonEnabled = sucursalController.sucursalIsNotEmpty(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SucursalFormContent(
    nombre: String,
    onNombreValueChange: (String) -> Unit,
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
                    value = municipio,
                    optionsList = municipiosList,
                    onValueChange = { onMunicipioValueChange(it) },
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
                    value = colonia,
                    textStyle = MaterialTheme.typography.h6,
                    colors = getCustomOutlinedTextFieldColor(),
                    onValueChange = onColoniaValueChange,
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
                    value = calle,
                    textStyle = MaterialTheme.typography.h6,
                    colors = getCustomOutlinedTextFieldColor(),
                    onValueChange = onCalleValueChange,
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
                    value = if (numero > 0) numero.toString() else "",
                    textStyle = MaterialTheme.typography.h6,
                    colors = getCustomOutlinedTextFieldColor(),
                    onValueChange = onNumeroValueChange,
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
                    value = codigoPostal,
                    textStyle = MaterialTheme.typography.h6,
                    colors = getCustomOutlinedTextFieldColor(),
                    onValueChange = onCodigoPostalValueChange,
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