package ui.venta

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import data.model.DetalleVentaProductoTestList

@Composable
fun VentaScreen() {
    Surface(color = MaterialTheme.colors.background) {
        VentaList(DetalleVentaProductoTestList)
    }
}