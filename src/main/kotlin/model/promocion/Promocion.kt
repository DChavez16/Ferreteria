package model.promocion

import model.producto.Producto

data class Promocion(
    // Primary key
    var id: Long? = null,

    // Atributes
    var description: String = "",
    var descuento: Double = 0.0,
    var disponibilidad: Boolean = false,

    // List of contents filled post retrieving
    var productos: List<Producto> = emptyList()
)