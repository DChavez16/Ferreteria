package ui.venta

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.VentaTestList

@Composable
fun VentaScreen() {
    Surface(color = MaterialTheme.colors.background) {
        VentaList(VentaTestList)
    }
}