package model.productoVenta

import Database
import model.producto.Producto
import model.promocion.Promocion
import model.venta.Venta

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


object ProductoVentaDatabase {
    private val statement = Database.connection.createStatement()

    fun createProductoVenta(productoVenta: ProductoVenta): Int {
        statement.executeUpdate(
            "execute createProductoVenta ${productoVenta.cantidad}, ${productoVenta.subtotal}, ${productoVenta.cantidadIVA + productoVenta.subtotal}, ${
                (productoVenta.subtotal + productoVenta.cantidadIVA) * (productoVenta.promocion?.descuento ?: 0.0)
            }, ${productoVenta.precioVenta}, ${productoVenta.producto.id}, ${productoVenta.promocion?.id}"
        )

        val query = statement.executeQuery("select idProductoVenta from ProductoVenta where idProductoVenta = IDENT_CURRENT('ProductoVenta')")

        var idProductoVenta = 0
        while(query.next()) {
            idProductoVenta = query.getInt("idProductoVenta")
        }

        return idProductoVenta
    }

    fun getProductoVentaPorVenta(venta: Venta) {

    }
}