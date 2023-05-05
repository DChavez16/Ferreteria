package data.model

data class ProductosVenta(
    var producto: Producto,
    var cantidad: Int,
    var subtotal: Double = producto.precioReal * cantidad,
    var cantidadIVA: Double = producto.cantidadIVA * cantidad,
    var promocion: Promocion? = null,
    var precioVenta: Double = (subtotal + cantidadIVA) * (promocion?.descuento ?: 1.0)
)
