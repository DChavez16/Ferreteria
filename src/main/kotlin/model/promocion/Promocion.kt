package model.promocion

import Database
import model.producto.Producto
import util.toBoolean
import util.toByte
import java.sql.Statement

data class Promocion(
    // Primary key
    var id: Int? = null,

    // Atributes
    var description: String = "",
    var descuento: Double = 0.0,
    var disponibilidad: Boolean = false,

    // List of contents filled post retrieving
    var productos: List<Producto> = emptyList()
)


object PromocionDatabase {
    private var statement: Statement = Database.connection.createStatement()

    /**
     * Get data for the indicated Promocion from the database
     * @param idPromocion ID of the Promocion which data will be retrieved from the database
     */
    fun getPromocion(idPromocion: Int): Promocion? {
        var promocion: Promocion? = null

        val query = statement.executeQuery("select * from vista_Promocion where idPromocion = $idPromocion")

        while(query.next()) {
            with(query) {
                promocion = Promocion(
                    id = getInt("idPromocion"),
                    description = getString("descripcion"),
                    descuento = getDouble("descuento"),
                    disponibilidad = query.getByte("disponibilidad").toBoolean()
                )
            }
        }

        return promocion
    }


    /**
     * Collects all the promotions stored in the database
     */
    fun getPromocionList(): List<Promocion> {
        val newList = mutableListOf<Promocion>()
        var currentPromocion: Promocion

        val query = statement.executeQuery("select * from vista_Promocion")

        while (query.next()) {
            currentPromocion = Promocion()

            currentPromocion.id = query.getInt("idPromocion")
            currentPromocion.description = query.getString("descripcion")
            currentPromocion.descuento = query.getDouble("descuento")
            currentPromocion.disponibilidad = query.getByte("disponibilidad").toBoolean()

            newList.add(currentPromocion)
        }

        return newList.toList()
    }


    /**
     * Inserts the promotion's data in the database
     * @param promocion Promocion to be added to the database
     */
    fun insertPromocion(promocion: Promocion): Boolean {
        val resultado =
            statement.executeUpdate(
                "execute insertPromocion '${promocion.description}', ${promocion.descuento}, ${promocion.disponibilidad.toByte()}"
            )

        val query = statement.executeQuery("select * from Promocion where idPromocion = IDENT_CURRENT('Promocion')")
        while (query.next()) {
            promocion.id = query.getInt("idPromocion")
        }

        promocion.productos.forEach { producto ->
            addPromotionToProduct(promocion.id!!, producto.id!!)
        }

        return resultado > 0
    }


    /**
     * Updates the indicated promotion's entry in the database
     * @param promocion Promocion entry to be updated at the database
     */
    fun updatePromocion(promocion: Promocion): Boolean {
        val resultado = statement.executeUpdate(
            "execute updatePromocion ${promocion.id}, '${promocion.description}', ${promocion.descuento}, ${promocion.disponibilidad.toByte()}"
        )

        promocion.productos.forEach { producto ->
            addPromotionToProduct(promocion.id!!, producto.id!!)
        }

        return resultado > 0
    }


    /**
     * Deletes the indicated promotion's entry in the database
     * @param promocion Promocion to be deleted from the database
     */
    fun deletePromocion(promocion: Promocion): Boolean {
        val resultado = statement.executeUpdate(
            "execute deletePromocion ${promocion.id}"
        )

        return resultado > 0
    }


    /**
     * Collects all products of the promotion from the database
     * @param idPromocion ID of the Promocion which purchases are going to be retrieved
     */
    fun getProductosPromocion(idPromocion: Int): List<Producto> {
        val newList = mutableListOf<Producto>()
        var currentProducto: Producto

        val query = statement.executeQuery("execute productosPorPromocion $idPromocion")

        while (query.next()) {
            currentProducto = Producto()

            currentProducto.id = query.getInt("idProducto")
            currentProducto.nombre = query.getString("nomProducto")
            currentProducto.precioReal = query.getDouble("preRealProducto")
            currentProducto.cantidadIVA = query.getDouble("cantIVAProducto")
            currentProducto.precioVenta = query.getDouble("preVenProducto")
            currentProducto.descripcion = query.getString("desProducto")

            newList.add(currentProducto)
        }

        return newList.toList()
    }


    /**
     * Inserts the DetalleProductoPromocion's data in the database
     * @param idPromocion ID of the Promocion to be added to the database
     * @param idProducto ID of the Producto to be added to the database
     */
    private fun addPromotionToProduct(idPromocion: Int, idProducto: Int): Boolean {
        val resultado =
            statement.executeUpdate(
                "execute addPromocionAProducto $idPromocion, $idProducto"
            )

        return resultado > 0
    }
}