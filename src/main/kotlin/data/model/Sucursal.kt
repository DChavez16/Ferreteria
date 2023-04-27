package data.model

data class Sucursal(
    var id: Long?,
    var name: String,
    val contacto: Contacto
)


val SucursalTestList = listOf(
    Sucursal(1, "San Nicolás", ContactoTestList[5]),
    Sucursal(2, "Apodaca", ContactoTestList[6]),
    Sucursal(3, "Monterrey", ContactoTestList[7]),
    Sucursal(4, "Escobedo", ContactoTestList[8]),
    Sucursal(5, "Guadalupe", ContactoTestList[9]),
    Sucursal(6, "San Pedro", ContactoTestList[10]),
)

fun getEmpleadosSucursal(id: Long): List<Empleado> {
    val newList = mutableListOf<Empleado>()

    EmpleadoTestList.forEach { empleado ->
        if(empleado.sucursal.id == id) newList.add(empleado)
    }

    return newList
}