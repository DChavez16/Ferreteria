package ui.cliente

import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import model.cliente.Cliente
import model.cliente.getFullName
import ui.util.BottomButtons

@Composable
fun ClienteList(
    clienteList: List<Cliente>,
    onAddClienteClick: () -> Unit,
    onEditClienteClick: (Cliente) -> Unit,
    onClienteElementClick: (Cliente) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        Surface(modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.fillMaxSize().border(width = Dp.Hairline, color = Color.Gray)) {
                // Cliente list header
                ClienteListHeader()

                // Cliente list content
                ClienteListContent(
                    clienteList = clienteList,
                    onEditClienteClick = onEditClienteClick,
                    onClienteElementClick = onClienteElementClick
                )
            }
        }

        BottomButtons(
            twoButtons = false,
            firstButtonText = "Agregar cliente",
            firstButtonAction = onAddClienteClick
        )
    }
}

@Composable
private fun ClienteListHeader() {
    Surface(elevation = 8.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "Id",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Nombre",
                modifier = Modifier.weight(2.5f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Suscripción",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Compras",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun ClienteListContent(
    clienteList: List<Cliente>,
    onEditClienteClick: (Cliente) -> Unit,
    onClienteElementClick: (Cliente) -> Unit
) {
    Box {
        val state = rememberLazyListState()

        LazyColumn(state = state) {
            items(clienteList) {
                ClienteListItem(it, onEditClienteClick, onClienteElementClick)
                Divider(color = Color.Gray, thickness = Dp.Hairline)
            }
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd),
            adapter = ScrollbarAdapter(scrollState = state)
        )
    }
}

@Composable
private fun ClienteListItem(
    cliente: Cliente,
    onEditClienteClick: (Cliente) -> Unit,
    onClienteElementClick: (Cliente) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClienteElementClick(cliente) }.padding(16.dp)
    ) {
        Text(
            text = "${cliente.id}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = cliente.getFullName(),
            modifier = Modifier.weight(2.5f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Left
        )
        Icon(
            imageVector = with(Icons.Default) { if (cliente.suscrito) Check else Close },
            contentDescription = null,
            tint = with(Color) { if (cliente.suscrito) Green else Red },
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "${cliente.cantidadCompras}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        IconButton(
            modifier = Modifier.weight(0.5f).height(20.dp),
            onClick = { onEditClienteClick(cliente) }
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}