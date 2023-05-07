package model.reporte

import model.fechaVenta.FechaVenta
import model.fechaVenta.FechaVentaTestList

data class Reporte(
    var id: FechaVenta?,
    var ventas: Int,
    var ventasPromocion: Int,
    var ingresos: Double
)

val ReporteTestList = listOf(
    Reporte(FechaVentaTestList[0], 50, 2, 700.0),
    Reporte(FechaVentaTestList[1], 20, 6, 300.0),
    Reporte(FechaVentaTestList[2], 30, 4, 500.0),
    Reporte(FechaVentaTestList[3], 40, 3, 400.0),
    Reporte(FechaVentaTestList[4], 60, 2, 700.0),
    Reporte(FechaVentaTestList[5], 70, 1, 100.0),
    Reporte(FechaVentaTestList[6], 40, 6, 800.0),
    Reporte(FechaVentaTestList[7], 20, 4, 200.0),
    Reporte(FechaVentaTestList[8], 30, 6, 900.0),
    Reporte(FechaVentaTestList[9], 60, 3, 300.0),
    Reporte(FechaVentaTestList[10], 50, 2, 700.0),
    Reporte(FechaVentaTestList[11], 20, 6, 300.0),
    Reporte(FechaVentaTestList[12], 30, 4, 500.0),
    Reporte(FechaVentaTestList[13], 40, 3, 400.0),
    Reporte(FechaVentaTestList[14], 60, 2, 700.0),
    Reporte(FechaVentaTestList[15], 70, 1, 100.0),
    Reporte(FechaVentaTestList[16], 40, 6, 800.0),
    Reporte(FechaVentaTestList[17], 20, 4, 200.0),
    Reporte(FechaVentaTestList[18], 30, 6, 900.0),
    Reporte(FechaVentaTestList[19], 60, 3, 300.0),
    Reporte(FechaVentaTestList[20], 50, 2, 700.0),
    Reporte(FechaVentaTestList[21], 20, 6, 300.0),
    Reporte(FechaVentaTestList[22], 30, 4, 500.0),
    Reporte(FechaVentaTestList[23], 40, 3, 400.0),
    Reporte(FechaVentaTestList[24], 60, 2, 700.0),
    Reporte(FechaVentaTestList[25], 70, 1, 100.0),
    Reporte(FechaVentaTestList[26], 40, 6, 800.0),
    Reporte(FechaVentaTestList[27], 20, 4, 200.0),
    Reporte(FechaVentaTestList[28], 30, 6, 900.0),
    Reporte(FechaVentaTestList[29], 60, 3, 300.0)
)