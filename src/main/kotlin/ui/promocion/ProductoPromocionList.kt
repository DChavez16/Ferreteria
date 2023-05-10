package ui.promocion

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import model.promocion.Promocion
import ui.util.ProductoPromocionDetailsList
import ui.util.ScreenHeader

@Composable
fun ProductoPromocionList(promocion: Promocion, onReturnButtonClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxHeight()) {
        // Draws the screen header, the header's title varies with the defined screen mode
        ScreenHeader(headerTitle = "Productos de la promoción", onReturnButtonClick = onReturnButtonClick)

        // Draws the promotion info
        PromotionDetails(promocion = promocion, modifier = Modifier.padding(16.dp))

        // Draws the table of the products included in the product
        ProductoPromocionDetailsList(
            productsList = promocion.productos,
            currentPromotionDiscount = promocion.descuento,
            modifier = Modifier.weight(1f).padding(16.dp)
        )
    }
}

@Composable
private fun PromotionDetails(promocion: Promocion, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "ID: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = "${promocion.id}", style = MaterialTheme.typography.h6, textDecoration = TextDecoration.Underline
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Descripción: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = promocion.description,
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Descuento: ", style = MaterialTheme.typography.h5)
            Spacer(Modifier.width(4.dp))
            Text(
                text = "${(promocion.descuento * 100).toInt()} %",
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}