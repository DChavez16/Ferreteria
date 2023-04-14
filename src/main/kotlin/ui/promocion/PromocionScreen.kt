package ui.promocion

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun PromocionScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Icon(painter = painterResource("icons/promocion.png"), contentDescription = null, modifier = Modifier.fillMaxSize())
    }

    // Promocion list screen
    PromocionList()

    // Producto Promocion list screen
    ProductoPromocionList()

    // Add Promocion screen
    AddPromocionScreen()

    // Edit Promocion screen
    EditPromocionScreen()
}