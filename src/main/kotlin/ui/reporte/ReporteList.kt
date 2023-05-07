package ui.reporte

import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import model.reporte.Reporte
import util.decimalFormat
import util.getFechaString

@Composable
fun ReporteList(
    reporteList: List<Reporte>
) {
    // Reporte list surface
    Surface(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        Column(modifier = Modifier.fillMaxSize().border(color = Color.Gray, width = Dp.Hairline)) {
            ReporteListHeader()
            ReporteListContent(reporteList = reporteList)
        }
    }
}

@Composable
private fun ReporteListHeader() {
    Surface(elevation = 8.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "Fecha",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Ventas",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Ventas por promoción",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Ingresos",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ReporteListContent(
    reporteList: List<Reporte>
) {
    Box {
        val state = rememberLazyListState()

        LazyColumn(state = state) {
            items(reporteList) {
                ReporteListItem(it)
                Divider(color = Color.Gray, thickness = Dp.Hairline)
            }
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd), adapter = ScrollbarAdapter(scrollState = state)
        )
    }
}

@Composable
private fun ReporteListItem(
    reporte: Reporte
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = getFechaString(reporte.id!!),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${reporte.ventas}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${reporte.ventasPromocion}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = "$ ${decimalFormat(reporte.ingresos)}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}