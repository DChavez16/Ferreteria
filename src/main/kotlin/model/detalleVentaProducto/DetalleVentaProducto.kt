package model.detalleVentaProducto

import Database
import model.producto.Producto
import model.productoVenta.ProductoVenta
import model.venta.Venta
import java.sql.Statement

data class DetalleVentaProducto(
    // Foreign key
    var venta: Venta = Venta(), var productos: List<ProductoVenta> = emptyList()
)


object DetalleVentaProductoDatabase {
    private var statement: Statement = Database.connection.createStatement()

    fun getProductosPorVenta(idVenta: Int): List<ProductoVenta> {
        val newList = mutableListOf<ProductoVenta>()
        var currentProductoVenta: ProductoVenta

        val query = statement.executeQuery("execute productosPorVenta $idVenta")

        while (query.next()) {
            with(query) {
                currentProductoVenta = ProductoVenta(
                    id = getInt("idProductoVenta"),
                    cantidad = getInt("cantidad"),
                    producto = Producto(
                        id = getInt("idProducto"),
                        nombre = getString("nomProducto")
                    ),
                    subtotal = getDouble("subtotal"),
                    cantidadIVA = getDouble("precioIVA"),
                    descripcionPromocion = getString("descripcionPromocion").let {
                        if (it == "null") null else it
                    },
                    precioVenta = getDouble("importe")
                )
            }

            newList.add(currentProductoVenta)
        }

        return newList.toList()
    }

    /**
     * Get ProductoVenta for the indicated Venta and Cliente from the database
     * @param idVenta ID of the Venta which ProductoVenta will be retrieved from the database
     * @param idCliente ID of the Cliente which ProductoVenta will be retrieved from the database
     */
    fun getProductosPorCompraPorCliente(idVenta: Int, idCliente: Int): List<ProductoVenta> {
        val newList = mutableListOf<ProductoVenta>()
        var currentProductoVenta: ProductoVenta

        val query = statement.executeQuery("execute productosPorCompraPorCliente $idVenta, $idCliente")

        while (query.next()) {
            with(query) {
                currentProductoVenta = ProductoVenta(
                    id = getInt("idProductoVenta"),
                    cantidad = getInt("cantidad"),
                    producto = Producto(
                        id = getInt("idProducto"),
                        nombre = getString("nomProducto")
                    ),
                    subtotal = getDouble("subtotal"),
                    cantidadIVA = getDouble("precioIVA"),
                    descripcionPromocion = getString("descripcionPromocion").let {
                        if (it == "null") null else it
                    },
                    precioVenta = getDouble("importe")
                )
            }

            newList.add(currentProductoVenta)
        }

        return newList.toList()
    }

    /**
     * Get ProductoVenta for the indicated Venta and Empleado from the database
     * @param idVenta ID of the Venta which ProductoVenta will be retrieved from the database
     * @param idEmpleado ID of the Empleado which ProductoVenta will be retrieved from the database
     */
    fun getProductosPorVentaPorEmpleado(idVenta: Int, idEmpleado: Int): List<ProductoVenta> {
        val newList = mutableListOf<ProductoVenta>()
        var currentProductoVenta: ProductoVenta

        val query = statement.executeQuery("execute productosPorVentaPorEmpleado $idVenta, $idEmpleado")

        while (query.next()) {
            with(query) {
                currentProductoVenta = ProductoVenta(
                    id = getInt("idProductoVenta"),
                    cantidad = getInt("cantidad"),
                    producto = Producto(
                        id = getInt("idProducto"),
                        nombre = getString("nomProducto")
                    ),
                    subtotal = getDouble("subtotal"),
                    cantidadIVA = getDouble("precioIVA"),
                    descripcionPromocion = getString("descripcionPromocion").let {
                        if (it == "null") null else it
                    },
                    precioVenta = getDouble("importe")
                )
            }

            newList.add(currentProductoVenta)
        }

        return newList.toList()
    }
}