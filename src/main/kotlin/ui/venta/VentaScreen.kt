package ui.venta

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controller.venta.VentaController
import ui.util.VentaList

@Composable
fun VentaScreen() {
    val ventaController = VentaController()

    Surface(color = MaterialTheme.colors.background) {
        VentaList(ventaController.salesList, modifier = Modifier.padding(32.dp))
    }
}