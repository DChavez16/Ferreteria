package model.productoVenta

import model.producto.Producto
import model.promocion.Promocion

data class ProductoVenta(
    // Primary key
    var id: Int? = null,

    // Atributes
    var cantidad: Int = 0,

    // Foreign key
    var producto: Producto = Producto(),
    // Atributes
    var subtotal: Double = producto.precioReal * cantidad, var cantidadIVA: Double = producto.cantidadIVA * cantidad,

    var promocion: Promocion? = producto.promocion,
    // Atributes
    var precioVenta: Double = (subtotal + cantidadIVA) * (if (promocion?.disponibilidad == true) 1.0 - promocion.descuento else 1.0)
)
