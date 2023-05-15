package model.sucursal

import model.contacto.Contacto

data class Sucursal(
    // Primary key
    var id: Long? = null,

    // Atributes
    var name: String = "",

    // Foreign key
    val contacto: Contacto = Contacto()
)