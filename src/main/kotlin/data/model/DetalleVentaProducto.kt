package data.model

import kotlin.random.Random

data class DetalleVentaProducto(
    var venta: Venta, var productos: List<ProductosVenta>, var reporte: Reporte
)


var DetalleVentaProductoTestList = listOf(
    DetalleVentaProducto(VentaTestList[0], getProductsList(), ReporteTestList[0]),
    DetalleVentaProducto(VentaTestList[1], getProductsList(), ReporteTestList[1]),
    DetalleVentaProducto(VentaTestList[2], getProductsList(), ReporteTestList[2]),
    DetalleVentaProducto(VentaTestList[3], getProductsList(), ReporteTestList[3]),
    DetalleVentaProducto(VentaTestList[4], getProductsList(), ReporteTestList[4]),
    DetalleVentaProducto(VentaTestList[5], getProductsList(), ReporteTestList[5]),
    DetalleVentaProducto(VentaTestList[6], getProductsList(), ReporteTestList[6]),
    DetalleVentaProducto(VentaTestList[7], getProductsList(), ReporteTestList[7]),
    DetalleVentaProducto(VentaTestList[8], getProductsList(), ReporteTestList[8]),
    DetalleVentaProducto(VentaTestList[9], getProductsList(), ReporteTestList[9]),
    DetalleVentaProducto(VentaTestList[10], getProductsList(), ReporteTestList[10]),
    DetalleVentaProducto(VentaTestList[11], getProductsList(), ReporteTestList[11]),
    DetalleVentaProducto(VentaTestList[12], getProductsList(), ReporteTestList[12]),
    DetalleVentaProducto(VentaTestList[13], getProductsList(), ReporteTestList[13]),
    DetalleVentaProducto(VentaTestList[14], getProductsList(), ReporteTestList[14]),
    DetalleVentaProducto(VentaTestList[15], getProductsList(), ReporteTestList[15]),
    DetalleVentaProducto(VentaTestList[16], getProductsList(), ReporteTestList[16]),
    DetalleVentaProducto(VentaTestList[17], getProductsList(), ReporteTestList[17]),
    DetalleVentaProducto(VentaTestList[18], getProductsList(), ReporteTestList[18]),
    DetalleVentaProducto(VentaTestList[19], getProductsList(), ReporteTestList[19]),
    DetalleVentaProducto(VentaTestList[20], getProductsList(), ReporteTestList[20]),
    DetalleVentaProducto(VentaTestList[21], getProductsList(), ReporteTestList[21]),
    DetalleVentaProducto(VentaTestList[22], getProductsList(), ReporteTestList[22]),
    DetalleVentaProducto(VentaTestList[23], getProductsList(), ReporteTestList[23]),
    DetalleVentaProducto(VentaTestList[24], getProductsList(), ReporteTestList[24]),
    DetalleVentaProducto(VentaTestList[25], getProductsList(), ReporteTestList[25]),
    DetalleVentaProducto(VentaTestList[26], getProductsList(), ReporteTestList[26]),
    DetalleVentaProducto(VentaTestList[27], getProductsList(), ReporteTestList[27]),
    DetalleVentaProducto(VentaTestList[28], getProductsList(), ReporteTestList[28]),
    DetalleVentaProducto(VentaTestList[29], getProductsList(), ReporteTestList[29]),
    DetalleVentaProducto(VentaTestList[30], getProductsList(), ReporteTestList[20]),
    DetalleVentaProducto(VentaTestList[31], getProductsList(), ReporteTestList[21]),
    DetalleVentaProducto(VentaTestList[32], getProductsList(), ReporteTestList[22]),
    DetalleVentaProducto(VentaTestList[33], getProductsList(), ReporteTestList[23]),
    DetalleVentaProducto(VentaTestList[34], getProductsList(), ReporteTestList[24]),
    DetalleVentaProducto(VentaTestList[35], getProductsList(), ReporteTestList[25]),
    DetalleVentaProducto(VentaTestList[36], getProductsList(), ReporteTestList[26]),
    DetalleVentaProducto(VentaTestList[37], getProductsList(), ReporteTestList[7]),
    DetalleVentaProducto(VentaTestList[38], getProductsList(), ReporteTestList[28]),
    DetalleVentaProducto(VentaTestList[39], getProductsList(), ReporteTestList[29])
)

private fun getProductsList(): List<ProductosVenta> {
    val productoList = mutableListOf<ProductosVenta>()

    (1..Random.nextInt(1, 10)).forEach { _ ->
        productoList.add(
            ProductosVenta(
                producto = ProductoTestList[Random.nextInt(0, 29)],
                cantidad = (1..10).random()
            )
        )
    }

    return productoList
}

fun getClientsPurchases(idCliente: Long): List<DetalleVentaProducto> {
    val newList = mutableListOf<DetalleVentaProducto>()

    DetalleVentaProductoTestList.forEach { detalleVentaProducto ->
        if (detalleVentaProducto.venta.cliente.id == idCliente) newList.add(detalleVentaProducto)
    }

    return newList
}