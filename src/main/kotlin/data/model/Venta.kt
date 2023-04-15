package data.model

data class Venta(
    var id: Long,
    var impRealVenta: Double,
    var ivaVenta: Double,
    var impIvaVenta: Double,
    var desVenta: Double,
    var netoVenta: Double,
    var idFechaVenta: Long,
    var idClente: Long,
    var idempleado: Long
)


val VentaTestList = listOf(
    Venta(1, 100.0, 16.0, 116.0, 0.0, 116.0, 1, 1, 1),
    Venta(2, 150.0, 24.0, 174.0, 50.0, 124.0, 1, 2, 2),
    Venta(3, 50.0, 8.0, 58.0, 0.0, 58.0, 2, 3, 2),
    Venta(4, 200.0, 32.0, 232.0, 75.0, 157.0, 2, 4, 3),
    Venta(5, 150.0, 24.0, 174.0, 0.0, 174.0, 2, 3, 1),
    Venta(6, 100.0, 16.0, 116.0, 25.0, 86.0, 3, 5, 2),
    Venta(7, 100.0, 16.0, 116.0, 0.0, 116.0, 3, 1, 1),
    Venta(8, 150.0, 24.0, 174.0, 50.0, 124.0, 4, 2, 4),
    Venta(9, 50.0, 8.0, 58.0, 0.0, 58.0, 4, 3, 2),
    Venta(10, 200.0, 32.0, 232.0, 75.0, 157.0, 4, 4, 3),
    Venta(11, 150.0, 24.0, 174.0, 0.0, 174.0, 5, 3, 1),
    Venta(12, 100.0, 16.0, 116.0, 25.0, 86.0, 6, 5, 4),
    Venta(13, 50.0, 8.0, 58.0, 0.0, 58.0, 7, 1, 2),
    Venta(14, 200.0, 32.0, 232.0, 75.0, 157.0, 7, 5, 3),
    Venta(15, 150.0, 24.0, 174.0, 0.0, 174.0, 7, 3, 1),
    Venta(16, 100.0, 16.0, 116.0, 25.0, 86.0, 8, 6, 4)
)