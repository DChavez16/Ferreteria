package data.model

data class Producto(
    var id: Long?,
    var nombre: String,
    var precioReal: Double,
    var cantidadIVA: Double,
    var precioVenta: Double,
    var descripcion: String
)


val ProductoTestList = listOf(
    Producto(1, "Producto 1", 100.0, 16.0, 116.0, "Descripción 1"),
    Producto(2, "Producto 2", 100.0, 16.0, 116.0, "Descripción 2"),
    Producto(3, "Producto 3", 50.0, 8.0, 58.0, "Descripción 3"),
    Producto(4, "Producto 4", 150.0, 24.0, 174.0, "Descripción 4"),
    Producto(5, "Producto 5", 50.0, 8.0, 58.0, "Descripción 5"),
    Producto(6, "Producto 6", 100.0, 16.0, 116.0, "Descripción 1"),
    Producto(7, "Producto 7", 100.0, 16.0, 116.0, "Descripción 2"),
    Producto(8, "Producto 8", 50.0, 8.0, 58.0, "Descripción 3"),
    Producto(9, "Producto 9", 150.0, 24.0, 174.0, "Descripción 4"),
    Producto(10, "Producto 10", 50.0, 8.0, 58.0, "Descripción 5"),
    Producto(11, "Producto 11", 100.0, 16.0, 116.0, "Descripción 11"),
    Producto(12, "Producto 12", 100.0, 16.0, 116.0, "Descripción 12"),
    Producto(13, "Producto 13", 50.0, 8.0, 58.0, "Descripción 13"),
    Producto(14, "Producto 14", 150.0, 24.0, 174.0, "Descripción 14"),
    Producto(15, "Producto 15", 50.0, 8.0, 58.0, "Descripción 15"),
    Producto(16, "Producto 16", 100.0, 16.0, 116.0, "Descripción 16"),
    Producto(17, "Producto 17", 100.0, 16.0, 116.0, "Descripción 17"),
    Producto(18, "Producto 18", 50.0, 8.0, 58.0, "Descripción 18"),
    Producto(19, "Producto 19", 150.0, 24.0, 174.0, "Descripción 19"),
    Producto(20, "Producto 20", 50.0, 8.0, 58.0, "Descripción 20"),
    Producto(21, "Producto 21", 100.0, 16.0, 116.0, "Descripción 21"),
    Producto(22, "Producto 22", 100.0, 16.0, 116.0, "Descripción 22"),
    Producto(23, "Producto 23", 50.0, 8.0, 58.0, "Descripción 23"),
    Producto(24, "Producto 24", 150.0, 24.0, 174.0, "Descripción 24"),
    Producto(25, "Producto 25", 50.0, 8.0, 58.0, "Descripción 25"),
    Producto(26, "Producto 26", 100.0, 16.0, 116.0, "Descripción 26"),
    Producto(27, "Producto 27", 100.0, 16.0, 116.0, "Descripción 27"),
    Producto(28, "Producto 28", 50.0, 8.0, 58.0, "Descripción 28"),
    Producto(29, "Producto 29", 150.0, 24.0, 174.0, "Descripción 29"),
    Producto(30, "Producto 30", 50.0, 8.0, 58.0, "Descripción 30"),
    Producto(31, "Producto 31", 100.0, 16.0, 116.0, "Descripción 31"),
    Producto(32, "Producto 32", 100.0, 16.0, 116.0, "Descripción 32"),
    Producto(33, "Producto 33", 50.0, 8.0, 58.0, "Descripción 33"),
    Producto(34, "Producto 34", 150.0, 24.0, 174.0, "Descripción 34"),
    Producto(35, "Producto 35", 50.0, 8.0, 58.0, "Descripción 35"),
    Producto(36, "Producto 36", 100.0, 16.0, 116.0, "Descripción 36"),
    Producto(37, "Producto 37", 100.0, 16.0, 116.0, "Descripción 37"),
    Producto(38, "Producto 38", 50.0, 8.0, 58.0, "Descripción 38"),
    Producto(39, "Producto 39", 150.0, 24.0, 174.0, "Descripción 39"),
    Producto(40, "Producto 40", 50.0, 8.0, 58.0, "Descripción 40")
)