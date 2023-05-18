package model.productoVenta

import Database
import model.producto.Producto

data class ProductoVenta(
    // Primary key
    var id: Int? = null,

    // Atributes
    var cantidad: Int = 0,

    // Foreign key
    var producto: Producto = Producto(),
    // Atributes
    var subtotal: Double = producto.precioReal * cantidad,
    var cantidadIVA: Double = producto.cantidadIVA * cantidad,

    var descripcionPromocion: String? = if (producto.promocion?.disponibilidad == true) producto.promocion?.description else null,
    // Atributes
    var precioVenta: Double = (subtotal + cantidadIVA).times(
        1.0 - if (producto.promocion?.disponibilidad == true) producto.promocion?.descuento ?: 0.0 else 0.0
    )
)


object ProductoVentaDatabase {
    private val statement = Database.connection.createStatement()

    fun createProductoVenta(productoVenta: ProductoVenta): Int {
        statement.executeUpdate(
            "execute createProductoVenta ${productoVenta.cantidad}, ${productoVenta.subtotal}, ${productoVenta.cantidadIVA}, '${productoVenta.descripcionPromocion}', ${productoVenta.precioVenta}, ${productoVenta.producto.id}"
        )

        val query =
            statement.executeQuery("select idProductoVenta from ProductoVenta where idProductoVenta = IDENT_CURRENT('ProductoVenta')")

        var idProductoVenta = 0
        while (query.next()) {
            idProductoVenta = query.getInt("idProductoVenta")
        }

        return idProductoVenta
    }
}