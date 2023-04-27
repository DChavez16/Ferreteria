package data.model

data class Promocion(
    var id: Long?,
    var description: String,
    var descuento: Double,
    var disponibilidad: Boolean,
    var productos: List<Producto>
)


val PromocionTestList = listOf(
    Promocion(1, "Promoción 5%", 0.05, true, listOf(
        ProductoTestList[1],
        ProductoTestList[5],
        ProductoTestList[7],
        ProductoTestList[10],
        ProductoTestList[35],
        ProductoTestList[27]
    )),
    Promocion(2, "Promoción 10%", 0.10, false, listOf(
        ProductoTestList[17],
        ProductoTestList[23],
        ProductoTestList[10],
        ProductoTestList[7],
        ProductoTestList[9],
        ProductoTestList[5]
    )),
    Promocion(3, "Promoción 15%", 0.15, false, listOf(
        ProductoTestList[2],
        ProductoTestList[6],
        ProductoTestList[25],
        ProductoTestList[38],
        ProductoTestList[36],
        ProductoTestList[20],
        ProductoTestList[8]
    )),
    Promocion(4, "Promoción 20%", 0.20, true, listOf(
        ProductoTestList[35],
        ProductoTestList[36],
        ProductoTestList[15]
    )),
    Promocion(5, "Promoción 25%", 0.25, true, listOf(
        ProductoTestList[18],
        ProductoTestList[2],
        ProductoTestList[21],
        ProductoTestList[30]
    )),
)