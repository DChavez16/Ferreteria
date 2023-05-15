package model.venta

import model.cliente.Cliente
import model.empleado.Empleado
import model.fechaVenta.FechaVenta

data class Venta(
    // Primary key
    var id: Int? = null,

    // Atributes
    var impRealVenta: Double = 0.0,
    var ivaVenta: Double = 0.0,
    var impIvaVenta: Double = 0.0,
    var desVenta: Double = 0.0,
    var netoVenta: Double = 0.0,

    // Foreign key
    var fechaVenta: FechaVenta = FechaVenta(),
    var cliente: Cliente = Cliente(),
    var empleado: Empleado = Empleado()
)