package model.promocion

import Database
import model.producto.Producto
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
    fun getPromocion(idPromocion: Int): Promocion {
        val promocion = Promocion(id = idPromocion)

        val query = statement.executeQuery("select * from vista_Promocion where idPromocion = $idPromocion")

        while(query.next()) {
            promocion.description = query.getString("descripcion")
            promocion.descuento = query.getDouble("descuento")
            promocion.disponibilidad = when(query.getByte("disponibilidad")) {
                0.toByte() -> false
                else -> true
            }
        }

        return promocion
    }

}