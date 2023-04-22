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
import data.model.ProveedorTestList

@Composable
fun ProveedorList(
    proveedorList: List<Proveedor>,
    onAddClicked: () -> Unit,
    onEditClicked: (Proveedor) -> Unit,
    onElementClicked: (Proveedor) -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Lista")
            Text(text = "Agregar", modifier = Modifier.clickable { onAddClicked() })
            Text(text = "Editar", modifier = Modifier.clickable { onEditClicked(ProveedorTestList[0]) })
            Text(text = "Elemento de la tabla", modifier = Modifier.clickable { onElementClicked(ProveedorTestList[0]) })
        }
    }
}