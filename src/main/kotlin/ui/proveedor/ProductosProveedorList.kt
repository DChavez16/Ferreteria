package ui.proveedor

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import data.model.Proveedor
import ui.util.ProductosProveedorDetailsList
import ui.util.ScreenHeader

@Composable
fun ProductosProveedorList(selectedProveedor: Proveedor, onReturnButtonClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Draws the screen header, the header's title varies with the defined screen mode
        ScreenHeader(headerTitle = "Productos del proveedor", onReturnButtonClick = onReturnButtonClick)

        // Draws the promotion info
        ProveedorDetails(proveedor = selectedProveedor, modifier = Modifier.padding(16.dp))

        // Draws the table of the products included in the product
        ProductosProveedorDetailsList(
            productosList = selectedProveedor.productos,
            modifier = Modifier.weight(1f).padding(16.dp)
        )
    }
}

@Composable
private fun ProveedorDetails(proveedor: Proveedor, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Row of supplier's name and id details
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
            // Row of name details
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Nombre: ", style = MaterialTheme.typography.h5)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = proveedor.nombre,
                    style = MaterialTheme.typography.h6,
                    textDecoration = TextDecoration.Underline
                )
            }

            // Row of id details
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Id: ", style = MaterialTheme.typography.h5)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "${proveedor.id}",
                    style = MaterialTheme.typography.h6,
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        // Row of supplier's email, phone and address details
        Row(modifier = Modifier.fillMaxWidth()) {
            // Row of email details
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Correo: ", style = MaterialTheme.typography.h5)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = proveedor.contacto.correo,
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
                    text = proveedor.contacto.telefono,
                    style = MaterialTheme.typography.h6,
                    textDecoration = TextDecoration.Underline
                )
            }

            // Row of address details
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Dirección: ", style = MaterialTheme.typography.h5)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = proveedor.contacto.direccion,
                    style = MaterialTheme.typography.h6,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}