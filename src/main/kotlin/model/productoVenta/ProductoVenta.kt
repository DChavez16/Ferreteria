package model.productoVenta

import model.producto.Producto
import model.promocion.Promocion

data class ProductoVenta(
    var producto: Producto,
    var cantidad: Int,
    var subtotal: Double = producto.precioReal * cantidad,
    var cantidadIVA: Double = producto.cantidadIVA * cantidad,
    var promocion: Promocion? = null,
    var precioVenta: Double = (subtotal + cantidadIVA) * (promocion?.descuento ?: 1.0)
)
