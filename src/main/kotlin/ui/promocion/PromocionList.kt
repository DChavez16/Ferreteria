package ui.promocion

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
import data.model.Promocion
import ui.util.BottomButtons

@Composable
fun PromocionList(
    promocionList: List<Promocion>,
    onAddPromocionClicked: () -> Unit,
    onEditPromocionClicked: (Promocion) -> Unit,
    onPromocionClicked: (Promocion) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        Surface(modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.fillMaxSize().border(width = Dp.Hairline, color = Color.Gray)) {
                PromocionListHeader()
                PromocionListContent(
                    promocionList = promocionList,
                    onPromocionClicked = onPromocionClicked,
                    onEditPromocionClicked = onEditPromocionClicked
                )
            }
        }
        BottomButtons(
            twoButtons = false,
            firstButtonText = "Agregar promocion",
            firstButtonAction = onAddPromocionClicked
        )
    }
}

@Composable
private fun PromocionListHeader() {
    Surface(elevation = 8.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "id",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Descripcion",
                modifier = Modifier.weight(2.5f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Descuento",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Disponible",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun PromocionListContent(
    promocionList: List<Promocion>,
    onPromocionClicked: (Promocion) -> Unit,
    onEditPromocionClicked: (Promocion) -> Unit
) {
    Box {
        val state = rememberLazyListState()

        LazyColumn(state = state) {
            items(promocionList) {
                PromocionListContentItem(it, onPromocionClicked, onEditPromocionClicked)
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
private fun PromocionListContentItem(
    promocion: Promocion,
    onPromocionClicked: (Promocion) -> Unit,
    onEditPromocionClicked: (Promocion) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onPromocionClicked(promocion) }.padding(16.dp)
    ) {
        Text(
            text = "${promocion.id}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Text(
            text = promocion.description,
            modifier = Modifier.weight(2.5f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Left
        )
        Text(
            text = "${(promocion.descuento * 100).toInt()} %",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = with(Icons.Default) { if (promocion.disponibilidad) Check else Close },
            contentDescription = null,
            tint = with(Color) { if (promocion.disponibilidad) Green else Red },
            modifier = Modifier.weight(1f)
        )
        IconButton(
            modifier = Modifier.weight(0.5f).height(20.dp),
            onClick = { onEditPromocionClicked(promocion) }
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}