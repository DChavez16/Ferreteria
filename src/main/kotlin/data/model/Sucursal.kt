package data.model

data class Sucursal(
    var id: Long?,
    var contacto: Contacto
)


val SucursalTestList = listOf(
    Sucursal(1, ContactoTestList[5]),
    Sucursal(2, ContactoTestList[6]),
    Sucursal(3, ContactoTestList[7]),
    Sucursal(4, ContactoTestList[8]),
    Sucursal(5, ContactoTestList[9]),
)