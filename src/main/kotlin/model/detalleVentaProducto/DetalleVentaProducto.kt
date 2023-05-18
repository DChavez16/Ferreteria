package model.detalleVentaProducto

import Database
import model.producto.ProductoDatabase
import model.productoVenta.ProductoVenta
import model.promocion.PromocionDatabase
import model.venta.Venta
import java.sql.Statement

data class DetalleVentaProducto(
    // Foreign key
    var venta: Venta = Venta(),
    var productos: List<ProductoVenta> = emptyList()
)


object DetalleVentaProductoDatabase {
    private var statement: Statement = Database.connection.createStatement()

    fun getProductosPorVenta(idVenta: Int): List<ProductoVenta> {
        val newList = mutableListOf<ProductoVenta>()
        var currentProductoVenta: ProductoVenta

        val query = statement.executeQuery("execute productosPorVenta $idVenta")

        while(query.next()) {
            with(query) {
                currentProductoVenta = ProductoVenta(
                    id = getInt("idProductoVenta"),
                    cantidad = query.getInt("cantidad"),
                    producto = ProductoDatabase.getProducto(getInt("idProducto")),
                    promocion = PromocionDatabase.getPromocion(query.getInt("idPromocion"))
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

        while(query.next()) {
            currentProductoVenta = ProductoVenta()

            currentProductoVenta.id = query.getInt("idProductoVenta")
            currentProductoVenta.cantidad = query.getInt("cantidad")
            currentProductoVenta.producto = ProductoDatabase.getProducto(query.getInt("idProducto"))
            currentProductoVenta.promocion = PromocionDatabase.getPromocion(query.getInt("idPromocion"))

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

        while(query.next()) {
            currentProductoVenta = ProductoVenta()

            currentProductoVenta.id = query.getInt("idProductoVenta")
            currentProductoVenta.cantidad = query.getInt("cantidad")
            currentProductoVenta.producto = ProductoDatabase.getProducto(query.getInt("idProducto"))
            currentProductoVenta.promocion = PromocionDatabase.getPromocion(query.getInt("idPromocion"))

            newList.add(currentProductoVenta)
        }

        return newList.toList()
    }
}