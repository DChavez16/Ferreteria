package model.fechaVenta

import Database

data class FechaVenta(
    // Primary key
    var id: Int? = null,

    // Atributes
    var dia: Int = 0,
    var mes: Int = 0,
    var anio: Int = 0
)


object FechaVentaDatabase {
    val statement = Database.connection.createStatement()

    fun getFechaVenta(): FechaVenta {
        val currentFechaVenta = FechaVenta()

        val query = statement.executeQuery("execute obtenerFechaVenta")

        while(query.next()) {
            currentFechaVenta.id = query.getInt("idFechaVenta")
            currentFechaVenta.dia = query.getInt("diaFechaVenta")
            currentFechaVenta.mes = query.getInt("mesFechaVenta")
            currentFechaVenta.anio = query.getInt("anioFechaVenta")
        }

        return currentFechaVenta
    }
}