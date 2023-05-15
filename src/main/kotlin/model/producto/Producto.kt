package model.producto

import model.proveedor.Proveedor

data class Producto(
    // Primary key
    var id: Long? = null,

    // Attributes
    var nombre: String = "",
    var precioReal: Double = 0.0,
    var cantidadIVA: Double = 0.0,
    var precioVenta: Double = 0.0,
    var descripcion: String = "",

    // Foreign key
    var proveedor: Proveedor = Proveedor()
)