package model.sucursal

import model.contacto.Contacto
import model.contacto.ContactoTestList

data class Sucursal(
    // Primary key
    var id: Long? = null,

    // Atributes
    var name: String = "",

    // Foreign key
    val contacto: Contacto = Contacto()
)


val SucursalTestList = listOf(
    Sucursal(1, "San Nicolás", ContactoTestList[5]),
    Sucursal(2, "Apodaca", ContactoTestList[6]),
    Sucursal(3, "Monterrey", ContactoTestList[7]),
    Sucursal(4, "Escobedo", ContactoTestList[8]),
    Sucursal(5, "Guadalupe", ContactoTestList[9]),
    Sucursal(6, "San Pedro", ContactoTestList[10]),
)