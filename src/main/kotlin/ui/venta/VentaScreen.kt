package ui.venta

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun VentaScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Icon(painter = painterResource("icons/venta.png"), contentDescription = null, modifier = Modifier.fillMaxSize())
    }

    // Venta list
    //VentaList()
}