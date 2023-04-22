package ui.proveedor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.model.Proveedor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProveedorInfoScreen(
    editable: Boolean,
    onReturnButtonClicked: () -> Unit,
    onMainButtonClicked: () -> Unit,
    onDeleteClicked: () -> Unit = {},
    selectedProveedor: Proveedor? = null
) {
    Box(contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = if (editable) "Editar" else "Agregar")
            Text(text = "Regresar", modifier = Modifier.onClick { onReturnButtonClicked() })
        }
    }
}