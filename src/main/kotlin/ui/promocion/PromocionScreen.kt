package ui.promocion

import androidx.compose.runtime.Composable

@Composable
fun PromocionScreen() {
    // Promocion list screen
    PromocionList()

    // Producto Promocion list screen
    ProductoPromocionList()

    // Add Promocion screen
    AddPromocionScreen()

    // Edit Promocion screen
    EditPromocionScreen()
}