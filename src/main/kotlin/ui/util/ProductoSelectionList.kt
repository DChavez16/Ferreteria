package ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
    Box(modifier = modifier.fillMaxHeight()) {
        val state = rememberLazyListState()

        LazyColumn(state = state) {
            items(productoList) {
                AvailableProductsListItem(it, onAddProductoClick, quantitySelectionEnabled)
            }
        }

        VerticalScrollbar(
            adapter = ScrollbarAdapter(scrollState = state),
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun AvailableProductsListItem(
    producto: Producto,
    onAddProductoClick: (Producto, Int) -> Unit,
    quantitySelectionEnabled: Boolean
) {
    var quantity by remember { mutableStateOf(if (quantitySelectionEnabled) 1 else null) }
    var decreaseButtonEnabled by remember { mutableStateOf(false) }

    Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(100.dp).padding(16.dp).fillMaxWidth()
        ) {
            Image(
                painter = painterResource("icons/producto.png"),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Text(
                text = producto.nombre,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.weight(1f)
            )
            Column(modifier = Modifier.weight(1f).fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                if (quantitySelectionEnabled) {
                    Row(modifier = Modifier.fillMaxWidth().weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                quantity = quantity?.minus(1); decreaseButtonEnabled =
                                validateDecreaseButtonEnabled(quantity!!)
                            },
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
                            modifier = Modifier.weight(2f),
                            textAlign = TextAlign.Center
                        )
                        IconButton(
                            onClick = {
                                quantity = quantity?.plus(1); decreaseButtonEnabled =
                                validateDecreaseButtonEnabled(quantity!!)
                            },
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
                Button(
                    onClick = { onAddProductoClick(producto, quantity ?: 0) },
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)
                ) {
                    Text(text = "Agregar", textAlign = TextAlign.Center, style = MaterialTheme.typography.button)
                }
            }
        }
    }
}

private fun validateDecreaseButtonEnabled(quantityValue: Int) = quantityValue > 1