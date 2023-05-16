package model.producto

import Database
import model.promocion.Promocion
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
    var proveedor: Proveedor = Proveedor(),
    var promocion: Promocion = Promocion()
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

    /**
     * Collects all the products stored in the database
     */
    fun getProductList(): List<Producto> {
        val newList = mutableListOf<Producto>()
        var currentProducto: Producto

        val query = statement.executeQuery(
            "select * from vista_Producto"
        )

        while (query.next()) {
            currentProducto = Producto()

            currentProducto.id = query.getInt("idProducto")
            currentProducto.nombre = query.getString("nombre")
            currentProducto.precioReal = query.getDouble("precioReal")
            currentProducto.cantidadIVA = query.getDouble("cantidadIVA")
            currentProducto.precioVenta = query.getDouble("precioVenta")
            currentProducto.descripcion = query.getString("descripcion")
            currentProducto.proveedor.id = query.getInt("idProveedor")
            currentProducto.proveedor.nombre = query.getString("nombreProveedor")

            newList.add(currentProducto)
        }

        return newList.toList()
    }


    /**
     * Inserts the product's data in the database
     * @param producto Producto to be added to the database
     */
    fun insertProducto(producto: Producto): Boolean {
        val resultado =
            statement.executeUpdate(
                "execute insertProducto '${producto.nombre}', ${producto.precioReal}, ${producto.cantidadIVA}, ${producto.precioVenta}, '${producto.descripcion}', ${producto.proveedor.id}"
            )

        return resultado > 0
    }


    /**
     * Updates the indicated product's entry in the database
     * @param producto Producto entry to be updated at the database
     */
    fun updateProducto(producto: Producto): Boolean {
        val resultado = statement.executeUpdate(
            "execute updateProducto ${producto.id}, '${producto.nombre}', ${producto.precioReal}, ${producto.cantidadIVA}, ${producto.precioVenta}, '${producto.descripcion}', ${producto.proveedor.id}"
        )

        return resultado > 0
    }


    /**
     * Deletes the indicated product's entry in the database
     * @param producto Producto to be deleted from the database
     */
    fun deleteProducto(producto: Producto): Boolean {
        val resultado = statement.executeUpdate(
            "execute deleteProducto ${producto.id}"
        )

        return resultado > 0
    }
}