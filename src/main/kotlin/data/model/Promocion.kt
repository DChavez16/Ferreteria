package data.model

data class Promocion(
    var id: Long?,
    var description: String,
    var descuento: Double,
    var disponibilidad: Boolean,
    var productos: List<Producto>
)

val PromocionTestList = listOf(
    Promocion(1, "Promocion 5%", 0.05, true, listOf()),
    Promocion(2, "Promocion 10%", 0.10, false, listOf(
        Producto(1, "Martillo", 100.0, 16.0, 116.0, "Martillo para martillear", 1),
        Producto(2, "Destornillador", 100.0, 16.0, 116.0, "Destornillador para destornillar", 2)
    )),
    Promocion(3, "Promocion 15%", 0.15, false, listOf()),
    Promocion(4, "Promocion 20%", 0.20, true, listOf(
        Producto(1, "Martillo", 100.0, 16.0, 116.0, "Martillo para martillear", 1),
        Producto(2, "Destornillador", 100.0, 16.0, 116.0, "Destornillador para destornillar", 2),
        Producto(4, "Cinta metrica", 150.0, 24.0, 174.0, "Cinta para medir", 3)
    )),
    Promocion(5, "Promocion 25%", 0.25, true, listOf(
        Producto(4, "Cinta metrica", 150.0, 24.0, 174.0, "Cinta para medir", 3)
    )),
)