package model.productoVenta

import model.producto.Producto
import model.promocion.Promocion

data class ProductoVenta(
    // Primary key
    var id: Long? = null,

    // Atributes
    var cantidad: Int,

    // Foreign key
    var producto: Producto = Producto(),
    // Atributes
    var subtotal: Double = producto.precioReal * cantidad,
    var cantidadIVA: Double = producto.cantidadIVA * cantidad,

    var promocion: Promocion = Promocion(),
    // Atributes
    var precioVenta: Double = (subtotal + cantidadIVA) * promocion.descuento,
)
