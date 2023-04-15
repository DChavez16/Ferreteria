package data.model

data class Producto(
    var id: Long?,
    var nombre: String,
    var precioReal: Double,
    var cantidadIVA: Double,
    var precioVenta: Double,
    var descripcion: String,
    var idProveedor: Long
)


val ProductoTestList = listOf(
    Producto(1, "Martillo", 100.0, 16.0, 116.0, "Martillo para martillear", 1),
    Producto(2, "Destornillador", 100.0, 16.0, 116.0, "Destornillador para destornillar", 2),
    Producto(3, "Caja de clavos", 50.0, 8.0, 58.0, "Clavos para clavar", 1),
    Producto(4, "Cinta metrica", 150.0, 24.0, 174.0, "Cinta para medir", 3),
    Producto(5, "Caja de tornillos", 50.0, 8.0, 58.0, "Tornillos para destornillar", 1)
)
