package ui.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import data.model.Cliente
import data.model.getClientsPurchases
import ui.util.ScreenHeader
import ui.util.VentaList

@Composable
fun VentaClienteList(
    selectedCliente: Cliente,
    onReturnButtonClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Draws the screen header, the header's title varies with the defined screen mode
        ScreenHeader(headerTitle = "Compras del cliente", onReturnButtonClick = onReturnButtonClick)

        // Draws the clients info
        ClienteDetails(
            cliente = selectedCliente,
            modifier = Modifier.padding(16.dp)
        )

        // Draws the table of the products included in the product
        VentaList(
            ventasList = getClientsPurchases(selectedCliente.id ?: 0),
            showEmpleado = true,
            modifier = Modifier.weight(1f).padding(16.dp)
        )
    }
}

@Composable
private fun ClienteDetails(
    cliente: Cliente,
    modifier: Modifier = Modifier
) {
    // Row of client's id, name, email and phone
    Row(modifier = modifier.fillMaxWidth()) {
        // Row of id details
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Id: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = "${cliente.id}",
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
        }

        // Row of name details
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Nombre: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = cliente.nombre,
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
        }

        // Row of email details
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Correo: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = cliente.contacto.correo,
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
        }

        // Row of phone details
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Teléfono: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = cliente.contacto.telefono,
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}