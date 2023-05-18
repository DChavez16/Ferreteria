package model.producto

import Database
import model.promocion.Promocion
import model.promocion.PromocionDatabase
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
    var promocion: Promocion? = null
)


object ProductoDatabase {
    private var statement: Statement = Database.connection.createStatement()

    /**
     * Get data for the indicated Producto from the database
     * @param idProducto ID of the Producto which data will be retrieved from the database
     */
    fun getProducto(idProducto: Int): Producto {
        var producto: Producto = Producto()

        val query = statement.executeQuery("select * from vista_Producto where idProducto = $idProducto")

        while (query.next()) {
            with(query) {
                producto = Producto(
                    id = getInt("idProducto"),
                    nombre = getString("nombre"),
                    precioReal = getDouble("precioReal"),
                    cantidadIVA = getDouble("cantidadIVA"),
                    precioVenta = getDouble("precioVenta"),
                    descripcion = getString("descripcion")
                )
            }
        }

        return producto
    }

    /**
     * Collects all the products stored in the database
     */
    fun getProductList(promocionFilter: Boolean = false): List<Producto> {
        val newList = mutableListOf<Producto>()
        var currentProducto: Producto

        val query = statement.executeQuery(
            "select * from vista_Producto${if (promocionFilter) " where idPromocion is null" else ""}"
        )

        while (query.next()) {
            currentProducto = Producto()

            with(query) {
                currentProducto = Producto(id = getInt("idProducto"),
                    nombre = getString("nombre"),
                    precioReal = getDouble("precioReal"),
                    cantidadIVA = getDouble("cantidadIVA"),
                    precioVenta = getDouble("precioVenta"),
                    descripcion = getString("descripcion"),
                    proveedor = Proveedor(
                        id = getInt("idProveedor"),
                        nombre = getString("nombreProveedor"),
                    ),
                    promocion = getInt("idPromocion").let {
                        if (it > 0) PromocionDatabase.getPromocion(it) else null
                    })
            }

            newList.add(currentProducto)
        }

        return newList.toList()
    }


    /**
     * Inserts the product's data in the database
     * @param producto Producto to be added to the database
     */
    fun insertProducto(producto: Producto): Boolean {
        val resultado = statement.executeUpdate(
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