package model.empleado

import model.sucursal.Sucursal
import model.sucursal.SucursalTestList
import model.venta.Venta
import model.venta.VentaTestList
import model.contacto.Contacto
import model.contacto.ContactoTestList
import model.detalleVentaProducto.DetalleVentaProducto
import model.detalleVentaProducto.DetalleVentaProductoTestList
import util.UserType

data class Empleado(
    var id: Long?,
    var nombre: String,
    var puesto: UserType,
    var contacto: Contacto,
    var sucursal: Sucursal,
    val sueldo: Double = if (puesto == UserType.ADMINISTRATOR) 15000.0 else 5000.0
)


val EmpleadoTestList = listOf(
    Empleado(1, "Empleado 1", UserType.ADMINISTRATOR, ContactoTestList[10], SucursalTestList[0]),
    Empleado(2, "Empleado 2", UserType.CASHIER, ContactoTestList[11], SucursalTestList[0]),
    Empleado(3, "Empleado 3", UserType.CASHIER, ContactoTestList[12], SucursalTestList[0]),
    Empleado(4, "Empleado 4", UserType.CASHIER, ContactoTestList[13], SucursalTestList[0]),
    Empleado(5, "Empleado 5", UserType.ADMINISTRATOR, ContactoTestList[14], SucursalTestList[1]),
    Empleado(6, "Empleado 6", UserType.CASHIER, ContactoTestList[15], SucursalTestList[1]),
    Empleado(7, "Empleado 7", UserType.CASHIER, ContactoTestList[16], SucursalTestList[1]),
    Empleado(8, "Empleado 8", UserType.CASHIER, ContactoTestList[17], SucursalTestList[1]),
    Empleado(9, "Empleado 9", UserType.ADMINISTRATOR, ContactoTestList[18], SucursalTestList[2]),
    Empleado(10, "Empleado 10", UserType.CASHIER, ContactoTestList[19], SucursalTestList[2]),
    Empleado(11, "Empleado 11", UserType.CASHIER, ContactoTestList[10], SucursalTestList[2]),
    Empleado(12, "Empleado 12", UserType.CASHIER, ContactoTestList[11], SucursalTestList[2]),
    Empleado(13, "Empleado 13", UserType.ADMINISTRATOR, ContactoTestList[12], SucursalTestList[3]),
    Empleado(14, "Empleado 14", UserType.CASHIER, ContactoTestList[13], SucursalTestList[3]),
    Empleado(15, "Empleado 15", UserType.CASHIER, ContactoTestList[14], SucursalTestList[3]),
    Empleado(16, "Empleado 16", UserType.CASHIER, ContactoTestList[15], SucursalTestList[3]),
    Empleado(17, "Empleado 17", UserType.ADMINISTRATOR, ContactoTestList[16], SucursalTestList[4]),
    Empleado(18, "Empleado 18", UserType.CASHIER, ContactoTestList[17], SucursalTestList[4]),
    Empleado(19, "Empleado 19", UserType.CASHIER, ContactoTestList[18], SucursalTestList[4]),
    Empleado(20, "Empleado 20", UserType.CASHIER, ContactoTestList[19], SucursalTestList[4])
)

fun getVentasEmpleado(id: Long?): List<Venta> {
    val newList = mutableListOf<Venta>()

    VentaTestList.forEach { venta ->
        if (venta.empleado.id == id) newList.add(venta)
    }

    return newList
}

fun getDetalleVentasEmpleado(id: Long?): List<DetalleVentaProducto> {
    val newList = mutableListOf<DetalleVentaProducto>()

    DetalleVentaProductoTestList.forEach { venta ->
        if (venta.venta.empleado.id == id) newList.add(venta)
    }

    return newList
}