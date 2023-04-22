package ui.cliente

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.model.Cliente
import data.model.ClienteTestList

@Composable
fun ClienteList(
    clienteList: List<Cliente>,
    onAddClienteClick: () -> Unit,
    onEditClienteClick: (Cliente) -> Unit,
    onClienteElementClick: (Cliente) -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Lista clientes")
            Text("Agregar", modifier = Modifier.clickable { onAddClienteClick() })
            Text("Editar", modifier = Modifier.clickable { onEditClienteClick(ClienteTestList[0]) })
            Text("Compras del cliente", modifier = Modifier.clickable { onClienteElementClick(ClienteTestList[0]) })
        }
    }
}