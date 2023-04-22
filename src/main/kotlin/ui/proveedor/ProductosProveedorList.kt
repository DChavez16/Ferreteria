package ui.proveedor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.model.Proveedor

@Composable
fun ProductosProveedorList(selectedProveedor: Proveedor, onReturnClicked: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Productos del proveedor")
            Text(text = "Regresar", modifier = Modifier.clickable { onReturnClicked() })
        }
    }
}