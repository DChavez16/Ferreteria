package data.model

import kotlin.random.Random

data class DetalleVentaProducto(
    var venta: Venta,
    var productos: List<Producto>,
    var promocion: Promocion,
    var reporte: Reporte
)


var DetalleVentaProductoTestList = listOf(
    DetalleVentaProducto(VentaTestList[0], getProductsList(), PromocionTestList[4], ReporteTestList[0]),
    DetalleVentaProducto(VentaTestList[1], getProductsList(), PromocionTestList[1], ReporteTestList[1]),
    DetalleVentaProducto(VentaTestList[2], getProductsList(), PromocionTestList[2], ReporteTestList[2]),
    DetalleVentaProducto(VentaTestList[3], getProductsList(), PromocionTestList[0], ReporteTestList[3]),
    DetalleVentaProducto(VentaTestList[4], getProductsList(), PromocionTestList[4], ReporteTestList[4]),
    DetalleVentaProducto(VentaTestList[5], getProductsList(), PromocionTestList[1], ReporteTestList[5]),
    DetalleVentaProducto(VentaTestList[6], getProductsList(), PromocionTestList[3], ReporteTestList[6]),
    DetalleVentaProducto(VentaTestList[7], getProductsList(), PromocionTestList[2], ReporteTestList[7]),
    DetalleVentaProducto(VentaTestList[8], getProductsList(), PromocionTestList[0], ReporteTestList[8]),
    DetalleVentaProducto(VentaTestList[9], getProductsList(), PromocionTestList[3], ReporteTestList[9]),
    DetalleVentaProducto(VentaTestList[10], getProductsList(), PromocionTestList[4], ReporteTestList[10]),
    DetalleVentaProducto(VentaTestList[11], getProductsList(), PromocionTestList[1], ReporteTestList[11]),
    DetalleVentaProducto(VentaTestList[12], getProductsList(), PromocionTestList[2], ReporteTestList[12]),
    DetalleVentaProducto(VentaTestList[13], getProductsList(), PromocionTestList[0], ReporteTestList[13]),
    DetalleVentaProducto(VentaTestList[14], getProductsList(), PromocionTestList[4], ReporteTestList[14]),
    DetalleVentaProducto(VentaTestList[15], getProductsList(), PromocionTestList[1], ReporteTestList[15]),
    DetalleVentaProducto(VentaTestList[16], getProductsList(), PromocionTestList[3], ReporteTestList[16]),
    DetalleVentaProducto(VentaTestList[17], getProductsList(), PromocionTestList[2], ReporteTestList[17]),
    DetalleVentaProducto(VentaTestList[18], getProductsList(), PromocionTestList[0], ReporteTestList[18]),
    DetalleVentaProducto(VentaTestList[19], getProductsList(), PromocionTestList[3], ReporteTestList[19]),
    DetalleVentaProducto(VentaTestList[20], getProductsList(), PromocionTestList[4], ReporteTestList[20]),
    DetalleVentaProducto(VentaTestList[21], getProductsList(), PromocionTestList[1], ReporteTestList[21]),
    DetalleVentaProducto(VentaTestList[22], getProductsList(), PromocionTestList[2], ReporteTestList[22]),
    DetalleVentaProducto(VentaTestList[23], getProductsList(), PromocionTestList[0], ReporteTestList[23]),
    DetalleVentaProducto(VentaTestList[24], getProductsList(), PromocionTestList[4], ReporteTestList[24]),
    DetalleVentaProducto(VentaTestList[25], getProductsList(), PromocionTestList[1], ReporteTestList[25]),
    DetalleVentaProducto(VentaTestList[26], getProductsList(), PromocionTestList[3], ReporteTestList[26]),
    DetalleVentaProducto(VentaTestList[27], getProductsList(), PromocionTestList[2], ReporteTestList[27]),
    DetalleVentaProducto(VentaTestList[28], getProductsList(), PromocionTestList[0], ReporteTestList[28]),
    DetalleVentaProducto(VentaTestList[29], getProductsList(), PromocionTestList[3], ReporteTestList[29]),
    DetalleVentaProducto(VentaTestList[30], getProductsList(), PromocionTestList[4], ReporteTestList[20]),
    DetalleVentaProducto(VentaTestList[31], getProductsList(), PromocionTestList[1], ReporteTestList[21]),
    DetalleVentaProducto(VentaTestList[32], getProductsList(), PromocionTestList[2], ReporteTestList[22]),
    DetalleVentaProducto(VentaTestList[33], getProductsList(), PromocionTestList[0], ReporteTestList[23]),
    DetalleVentaProducto(VentaTestList[34], getProductsList(), PromocionTestList[4], ReporteTestList[24]),
    DetalleVentaProducto(VentaTestList[35], getProductsList(), PromocionTestList[1], ReporteTestList[25]),
    DetalleVentaProducto(VentaTestList[36], getProductsList(), PromocionTestList[3], ReporteTestList[26]),
    DetalleVentaProducto(VentaTestList[37], getProductsList(), PromocionTestList[2], ReporteTestList[7]),
    DetalleVentaProducto(VentaTestList[38], getProductsList(), PromocionTestList[0], ReporteTestList[28]),
    DetalleVentaProducto(VentaTestList[39], getProductsList(), PromocionTestList[3], ReporteTestList[29])
)

private fun getProductsList(): List<Producto> {
    val productoList = mutableListOf<Producto>()
    var currentProduct: Producto

    (1..Random.nextInt(1, 5)).forEach {
        currentProduct = ProductoTestList[Random.nextInt(0, 29)]

        if(!productoList.contains(currentProduct)) {
            productoList.add(currentProduct)
        }
    }

    return productoList
}