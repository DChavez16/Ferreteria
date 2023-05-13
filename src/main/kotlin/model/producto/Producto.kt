package model.producto

import model.proveedor.Proveedor
import model.proveedor.ProveedorTestList

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


val ProductoTestList = listOf(
    Producto(1, "Producto 1", 100.0, 16.0, 116.0, "Descripción 1", ProveedorTestList[0]),
    Producto(2, "Producto 2", 100.0, 16.0, 116.0, "Descripción 2", ProveedorTestList[0]),
    Producto(3, "Producto 3", 50.0, 8.0, 58.0, "Descripción 3", ProveedorTestList[0]),
    Producto(4, "Producto 4", 150.0, 24.0, 174.0, "Descripción 4", ProveedorTestList[0]),
    Producto(5, "Producto 5", 50.0, 8.0, 58.0, "Descripción 5", ProveedorTestList[0]),
    Producto(6, "Producto 6", 100.0, 16.0, 116.0, "Descripción 1", ProveedorTestList[0]),
    Producto(7, "Producto 7", 100.0, 16.0, 116.0, "Descripción 2", ProveedorTestList[1]),
    Producto(8, "Producto 8", 50.0, 8.0, 58.0, "Descripción 3", ProveedorTestList[1]),
    Producto(9, "Producto 9", 150.0, 24.0, 174.0, "Descripción 4", ProveedorTestList[1]),
    Producto(10, "Producto 10", 50.0, 8.0, 58.0, "Descripción 5", ProveedorTestList[1]),
    Producto(11, "Producto 11", 100.0, 16.0, 116.0, "Descripción 11", ProveedorTestList[1]),
    Producto(12, "Producto 12", 100.0, 16.0, 116.0, "Descripción 12", ProveedorTestList[2]),
    Producto(13, "Producto 13", 50.0, 8.0, 58.0, "Descripción 13", ProveedorTestList[2]),
    Producto(14, "Producto 14", 150.0, 24.0, 174.0, "Descripción 14", ProveedorTestList[2]),
    Producto(15, "Producto 15", 50.0, 8.0, 58.0, "Descripción 15", ProveedorTestList[2]),
    Producto(16, "Producto 16", 100.0, 16.0, 116.0, "Descripción 16", ProveedorTestList[2]),
    Producto(17, "Producto 17", 100.0, 16.0, 116.0, "Descripción 17", ProveedorTestList[3]),
    Producto(18, "Producto 18", 50.0, 8.0, 58.0, "Descripción 18", ProveedorTestList[3]),
    Producto(19, "Producto 19", 150.0, 24.0, 174.0, "Descripción 19", ProveedorTestList[3]),
    Producto(20, "Producto 20", 50.0, 8.0, 58.0, "Descripción 20", ProveedorTestList[3]),
    Producto(21, "Producto 21", 100.0, 16.0, 116.0, "Descripción 21", ProveedorTestList[3]),
    Producto(22, "Producto 22", 100.0, 16.0, 116.0, "Descripción 22", ProveedorTestList[4]),
    Producto(23, "Producto 23", 50.0, 8.0, 58.0, "Descripción 23", ProveedorTestList[4]),
    Producto(24, "Producto 24", 150.0, 24.0, 174.0, "Descripción 24", ProveedorTestList[4]),
    Producto(25, "Producto 25", 50.0, 8.0, 58.0, "Descripción 25", ProveedorTestList[4]),
    Producto(26, "Producto 26", 100.0, 16.0, 116.0, "Descripción 26", ProveedorTestList[4]),
    Producto(27, "Producto 27", 100.0, 16.0, 116.0, "Descripción 27", ProveedorTestList[0]),
    Producto(28, "Producto 28", 50.0, 8.0, 58.0, "Descripción 28", ProveedorTestList[0]),
    Producto(29, "Producto 29", 150.0, 24.0, 174.0, "Descripción 29", ProveedorTestList[0]),
    Producto(30, "Producto 30", 50.0, 8.0, 58.0, "Descripción 30", ProveedorTestList[0]),
    Producto(31, "Producto 31", 100.0, 16.0, 116.0, "Descripción 31", ProveedorTestList[0]),
    Producto(32, "Producto 32", 100.0, 16.0, 116.0, "Descripción 32", ProveedorTestList[1]),
    Producto(33, "Producto 33", 50.0, 8.0, 58.0, "Descripción 33", ProveedorTestList[2]),
    Producto(34, "Producto 34", 150.0, 24.0, 174.0, "Descripción 34", ProveedorTestList[3]),
    Producto(35, "Producto 35", 50.0, 8.0, 58.0, "Descripción 35", ProveedorTestList[4]),
    Producto(36, "Producto 36", 100.0, 16.0, 116.0, "Descripción 36", ProveedorTestList[0]),
    Producto(37, "Producto 37", 100.0, 16.0, 116.0, "Descripción 37", ProveedorTestList[2]),
    Producto(38, "Producto 38", 50.0, 8.0, 58.0, "Descripción 38", ProveedorTestList[3]),
    Producto(39, "Producto 39", 150.0, 24.0, 174.0, "Descripción 39", ProveedorTestList[4]),
    Producto(40, "Producto 40", 50.0, 8.0, 58.0, "Descripción 40", ProveedorTestList[1])
)