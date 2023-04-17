package ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.model.Producto

@Composable
fun AvailableProductsList(
    modifier: Modifier = Modifier,
    productoList: List<Producto>,
    quantitySelectionEnabled: Boolean = false,
    onAddProductoClick: (Producto, Int?) -> Unit
) {
    Column(modifier = modifier.fillMaxHeight()) {
        //Text(text = "Seleccionar cliente plaheholder")
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp), modifier = Modifier.weight(1f)
        ) {
            items(productoList) {
                AvailableProductsListItem(it, onAddProductoClick, quantitySelectionEnabled)
            }
        }
    }
}

@Composable
private fun AvailableProductsListItem(
    producto: Producto,
    onAddProductoClick: (Producto, Int) -> Unit,
    quantitySelectionEnabled: Boolean
) {
    var quantity by remember { mutableStateOf(if(quantitySelectionEnabled) 1 else null) }
    var decreaseButtonEnabled by remember { mutableStateOf(false) }

    Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource("icons/producto.png"),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = producto.nombre,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.fillMaxWidth()
            )
            if (quantitySelectionEnabled) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { quantity = quantity?.minus(1); decreaseButtonEnabled = validateDecreaseButtonEnabled(quantity!!) },
                        modifier = Modifier.weight(1f),
                        enabled = decreaseButtonEnabled
                    ) {
                        Icon(
                            painterResource("icons/minus.png"),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    Text(
                        text = "$quantity",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.weight(2f).fillMaxHeight(),
                        textAlign = TextAlign.Center
                    )
                    IconButton(
                        onClick = { quantity = quantity?.plus(1); decreaseButtonEnabled = validateDecreaseButtonEnabled(quantity!!) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painterResource("icons/add.png"),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            Button(onClick = { onAddProductoClick(producto, quantity ?: 0) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Agregar", textAlign = TextAlign.Center, style = MaterialTheme.typography.button)
            }
        }
    }
}

private fun validateDecreaseButtonEnabled(quantityValue: Int) = quantityValue > 1