package model.fechaVenta

data class FechaVenta(
    // Primary key
    var id: Long? = null,

    // Atributes
    var dia: Int = 0,
    var mes: Int = 0,
    var anio: Int = 0
)