package model.reporte

import model.fechaVenta.FechaVenta

data class Reporte(
    // Primary key
    var id: Long? = null,

    // Atributes
    var ventas: Int = 0,
    var ventasPromocion: Int = 0,
    var ingresos: Double = 0.0,

    // Foreign key
    var fechaVenta: FechaVenta? = null,
)