package model.reporte

import Database
import model.fechaVenta.FechaVenta

data class Reporte(
    // Primary key
    var id: Int? = null,

    // Atributes
    var ventas: Int = 0,
    var ventasPromocion: Int = 0,
    var ingresos: Double = 0.0,

    // Foreign key
    var fechaVenta: FechaVenta? = null,
)



object ReporteDatabase {
    private val statement = Database.connection.createStatement()

    fun getReporteList(): List<Reporte> {
        val newList = mutableListOf<Reporte>()
        var currentReporte: Reporte

        val query = statement.executeQuery("select * from vista_Reporte")

        while (query.next()) {
            currentReporte = Reporte()

            with(query) {
                currentReporte.id = getInt("idReporte")
                currentReporte.ventas = getInt("ventas")
                currentReporte.ventasPromocion = getInt("ventasPromocion")
                currentReporte.ingresos = getDouble("ingresos")
                currentReporte.fechaVenta = FechaVenta(
                    id = getInt("idFecha"),
                    dia = getInt("dia"),
                    mes = getInt("mes"),
                    anio = getInt("anio")
                )
            }

            newList.add(currentReporte)
        }

        return newList.toList()
    }

    fun updateReporte(reporte: Reporte): Boolean {
        val resultado =
            statement.executeUpdate(
                "execute upsertReporte ${reporte.ventasPromocion}, ${reporte.ingresos}, ${reporte.fechaVenta!!.id}"
            )

        return resultado > 0
    }
}