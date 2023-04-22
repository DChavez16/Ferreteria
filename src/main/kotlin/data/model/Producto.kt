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
    Producto(1, "Producto 1", 100.0, 16.0, 116.0, "Descripcion 1"),
    Producto(2, "Producto 2", 100.0, 16.0, 116.0, "Descripcion 2"),
    Producto(3, "Producto 3", 50.0, 8.0, 58.0, "Descripcion 3"),
    Producto(4, "Producto 4", 150.0, 24.0, 174.0, "Descripcion 4"),
    Producto(5, "Producto 5", 50.0, 8.0, 58.0, "Descripcion 5"),
    Producto(6, "Producto 6", 100.0, 16.0, 116.0, "Descripcion 1"),
    Producto(7, "Producto 7", 100.0, 16.0, 116.0, "Descripcion 2"),
    Producto(8, "Producto 8", 50.0, 8.0, 58.0, "Descripcion 3"),
    Producto(9, "Producto 9", 150.0, 24.0, 174.0, "Descripcion 4"),
    Producto(10, "Producto 10", 50.0, 8.0, 58.0, "Descripcion 5"),
    Producto(11, "Producto 11", 100.0, 16.0, 116.0, "Descripcion 11"),
    Producto(12, "Producto 12", 100.0, 16.0, 116.0, "Descripcion 12"),
    Producto(13, "Producto 13", 50.0, 8.0, 58.0, "Descripcion 13"),
    Producto(14, "Producto 14", 150.0, 24.0, 174.0, "Descripcion 14"),
    Producto(15, "Producto 15", 50.0, 8.0, 58.0, "Descripcion 15"),
    Producto(16, "Producto 16", 100.0, 16.0, 116.0, "Descripcion 16"),
    Producto(17, "Producto 17", 100.0, 16.0, 116.0, "Descripcion 17"),
    Producto(18, "Producto 18", 50.0, 8.0, 58.0, "Descripcion 18"),
    Producto(19, "Producto 19", 150.0, 24.0, 174.0, "Descripcion 19"),
    Producto(20, "Producto 20", 50.0, 8.0, 58.0, "Descripcion 20"),
    Producto(21, "Producto 21", 100.0, 16.0, 116.0, "Descripcion 21"),
    Producto(22, "Producto 22", 100.0, 16.0, 116.0, "Descripcion 22"),
    Producto(23, "Producto 23", 50.0, 8.0, 58.0, "Descripcion 23"),
    Producto(24, "Producto 24", 150.0, 24.0, 174.0, "Descripcion 24"),
    Producto(25, "Producto 25", 50.0, 8.0, 58.0, "Descripcion 25"),
    Producto(26, "Producto 26", 100.0, 16.0, 116.0, "Descripcion 26"),
    Producto(27, "Producto 27", 100.0, 16.0, 116.0, "Descripcion 27"),
    Producto(28, "Producto 28", 50.0, 8.0, 58.0, "Descripcion 28"),
    Producto(29, "Producto 29", 150.0, 24.0, 174.0, "Descripcion 29"),
    Producto(30, "Producto 30", 50.0, 8.0, 58.0, "Descripcion 30"),
    Producto(31, "Producto 31", 100.0, 16.0, 116.0, "Descripcion 31"),
    Producto(32, "Producto 32", 100.0, 16.0, 116.0, "Descripcion 32"),
    Producto(33, "Producto 33", 50.0, 8.0, 58.0, "Descripcion 33"),
    Producto(34, "Producto 34", 150.0, 24.0, 174.0, "Descripcion 34"),
    Producto(35, "Producto 35", 50.0, 8.0, 58.0, "Descripcion 35"),
    Producto(36, "Producto 36", 100.0, 16.0, 116.0, "Descripcion 36"),
    Producto(37, "Producto 37", 100.0, 16.0, 116.0, "Descripcion 37"),
    Producto(38, "Producto 38", 50.0, 8.0, 58.0, "Descripcion 38"),
    Producto(39, "Producto 39", 150.0, 24.0, 174.0, "Descripcion 39"),
    Producto(40, "Producto 40", 50.0, 8.0, 58.0, "Descripcion 40")
)