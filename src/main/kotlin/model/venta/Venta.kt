package model.venta

import Database
import model.cliente.Cliente
import model.empleado.Empleado
import model.fechaVenta.FechaVenta
import model.productoVenta.ProductoVenta

data class Venta(
    // Primary key
    var id: Int? = null,

    // Atributes
    var impRealVenta: Double = 0.0,
    var ivaVenta: Double = 0.0,
    var impIvaVenta: Double = 0.0,
    var desVenta: Double = 0.0,
    var netoVenta: Double = 0.0,

    // Foreign key
    var fechaVenta: FechaVenta = FechaVenta(),
    var cliente: Cliente = Cliente(),
    var empleado: Empleado = Empleado()
)


object VentaDatabase {
    private val statement = Database.connection.createStatement()

    fun makeVenta(venta: Venta): Int {
        statement.executeUpdate(
            "execute efectuarVenta ${venta.fechaVenta.id}, ${venta.cliente.id}, ${venta.empleado.id}"
        )

        val query = statement.executeQuery("select idVenta from Venta where idVenta = IDENT_CURRENT('Venta')")

        var idVenta = 0
        while (query.next()) {
            idVenta = query.getInt("idVenta")
        }

        return idVenta
    }

    fun updateVenta(idVenta: Int, productoVenta: ProductoVenta): Boolean {
        val resultado = statement.executeUpdate(
            "execute updateVenta $idVenta, ${productoVenta.subtotal}, ${
                (productoVenta.subtotal + productoVenta.cantidadIVA) * (if (productoVenta.promocion?.disponibilidad == true) 1.0 - productoVenta.promocion!!.descuento else 1.0)
            }"
        )

        return resultado > 0
    }

    fun createDetalleProductoVenta(idProductoVenta: Int, idVenta: Int): Boolean {
        val resultado = statement.executeUpdate(
            "execute createDetalleProductoVenta $idProductoVenta, $idVenta"
        )

        return resultado > 0
    }
}