package model.contacto

import model.direccion.Direccion

data class Contacto(
    // Primary key
    var id: Long? = null,

    // Atributes
    var correo: String = "",
    var telefono: String = "",

    // Foreign key
    var direccion: Direccion = Direccion()
)