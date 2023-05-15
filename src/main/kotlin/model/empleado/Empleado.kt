package model.empleado

import model.contacto.Contacto
import model.detalleVentaProducto.DetalleVentaProducto
import model.sucursal.Sucursal
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