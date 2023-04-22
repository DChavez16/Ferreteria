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
import data.model.SucursalTestList

@Composable
fun SucursalList(
    sucursalList: List<Sucursal>,
    onAddSucursalClick: () -> Unit,
    onEditSucursalClick: (Sucursal) -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Lista sucursal")
            Text("Agregar", modifier = Modifier.clickable { onAddSucursalClick() })
            Text("Editar", modifier = Modifier.clickable { onEditSucursalClick(SucursalTestList[0]) })
        }
    }
}