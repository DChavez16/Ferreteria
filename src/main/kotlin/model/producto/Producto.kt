package model.producto

import Database
import model.proveedor.Proveedor
import java.sql.Statement

data class Producto(
    // Primary key
    var id: Int? = null,

    // Attributes
    var nombre: String = "",
    var precioReal: Double = 0.0,
    var cantidadIVA: Double = 0.0,
    var precioVenta: Double = 0.0,
    var descripcion: String = "",

    // Foreign key
    var proveedor: Proveedor = Proveedor()
)


object ProductoDatabase {
    private var statement: Statement = Database.connection.createStatement()

    /**
     * Get data for the indicated Producto from the database
     * @param idProducto ID of the Producto which data will be retrieved from the database
     */
    fun getProducto(idProducto: Int): Producto {
        val producto = Producto(id = idProducto)

        val query = statement.executeQuery("select * from vista_Producto where idProducto = $idProducto")

        while(query.next()) {
            producto.nombre = query.getString("nombre")
            producto.precioReal = query.getDouble("precioReal")
            producto.cantidadIVA = query.getDouble("cantidadIVA")
            producto.precioVenta = query.getDouble("precionVenta")
            producto.descripcion = query.getString("descripcion")
        }

        return producto
    }

}