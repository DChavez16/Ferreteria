package ui.sucursal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.model.Sucursal

@Composable
fun SucursalInfoScreen(
    editable: Boolean,
    onReturnButtonClick: () -> Unit,
    onMainButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit = {},
    selectedSucursal: Sucursal? = null
) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(if(editable) "Editar" else "Agregar")
            Text("Regresar", modifier = Modifier.clickable { onReturnButtonClick() })
        }
    }
}