package model.fechaVenta

import Database

data class FechaVenta(
    // Primary key
    var id: Int? = null,

    // Atributes
    var dia: Int = 0, var mes: Int = 0, var anio: Int = 0
)


object FechaVentaDatabase {
    private val statement = Database.connection.createStatement()

    fun getFechaVenta(): FechaVenta {
        var currentFechaVenta = FechaVenta()

        val query = statement.executeQuery("execute obtenerFechaVenta")

        while (query.next()) {
            with(query) {
                currentFechaVenta = FechaVenta(
                    id = getInt("idFechaVenta"),
                    dia = getInt("diaFechaVenta"),
                    mes = getInt("mesFechaVenta"),
                    anio = getInt("anioFechaVenta")
                )
            }
        }

        return currentFechaVenta
    }
}