package model.empleado

import model.contacto.Contacto
import model.contacto.ContactoTestList
import model.detalleVentaProducto.DetalleVentaProducto
import model.sucursal.Sucursal
import model.sucursal.SucursalTestList
import util.UserType

data class Empleado(
    // Primary key
    var id: Long? = null,

    // Attributes
    var nombre: String = "",
    var puesto: UserType = UserType.CASHIER,
    var cantidadVentas: Int = 0,

    // Foreign key
    var contacto: Contacto = Contacto(),
    var sucursal: Sucursal = Sucursal(),

    // List of contents filled post retrieving
    var listaVentas: List<DetalleVentaProducto> = emptyList(),

    // Helper variables
    val sueldo: Double = if (puesto == UserType.ADMINISTRATOR) 15000.0 else 5000.0
)


val EmpleadoTestList = listOf(
    Empleado(1, "Empleado 1", UserType.ADMINISTRATOR, cantidadVentas = 0, ContactoTestList[10], SucursalTestList[0]),
    Empleado(2, "Empleado 2", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[11], SucursalTestList[0]),
    Empleado(3, "Empleado 3", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[12], SucursalTestList[0]),
    Empleado(4, "Empleado 4", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[13], SucursalTestList[0]),
    Empleado(5, "Empleado 5", UserType.ADMINISTRATOR, cantidadVentas = 0, ContactoTestList[14], SucursalTestList[1]),
    Empleado(6, "Empleado 6", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[15], SucursalTestList[1]),
    Empleado(7, "Empleado 7", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[16], SucursalTestList[1]),
    Empleado(8, "Empleado 8", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[17], SucursalTestList[1]),
    Empleado(9, "Empleado 9", UserType.ADMINISTRATOR, cantidadVentas = 0, ContactoTestList[18], SucursalTestList[2]),
    Empleado(10, "Empleado 10", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[19], SucursalTestList[2]),
    Empleado(11, "Empleado 11", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[10], SucursalTestList[2]),
    Empleado(12, "Empleado 12", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[11], SucursalTestList[2]),
    Empleado(13, "Empleado 13", UserType.ADMINISTRATOR, cantidadVentas = 0, ContactoTestList[12], SucursalTestList[3]),
    Empleado(14, "Empleado 14", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[13], SucursalTestList[3]),
    Empleado(15, "Empleado 15", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[14], SucursalTestList[3]),
    Empleado(16, "Empleado 16", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[15], SucursalTestList[3]),
    Empleado(17, "Empleado 17", UserType.ADMINISTRATOR, cantidadVentas = 0, ContactoTestList[16], SucursalTestList[4]),
    Empleado(18, "Empleado 18", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[17], SucursalTestList[4]),
    Empleado(19, "Empleado 19", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[18], SucursalTestList[4]),
    Empleado(20, "Empleado 20", UserType.CASHIER, cantidadVentas = 0, ContactoTestList[19], SucursalTestList[4])
)