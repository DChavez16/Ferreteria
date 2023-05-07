package model.venta

import model.cliente.Cliente
import model.cliente.ClienteTestList
import model.empleado.Empleado
import model.empleado.EmpleadoTestList
import model.fechaVenta.FechaVenta
import model.fechaVenta.FechaVentaTestList

data class Venta(
    var id: Long,
    var impRealVenta: Double,
    var ivaVenta: Double,
    var impIvaVenta: Double,
    var desVenta: Double,
    var netoVenta: Double,
    var fechaVenta: FechaVenta,
    var cliente: Cliente,
    var empleado: Empleado
)


val VentaTestList = listOf(
    Venta(1, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[0], ClienteTestList[0], EmpleadoTestList[0]),
    Venta(2, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[0], ClienteTestList[1], EmpleadoTestList[5]),
    Venta(3, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[0], ClienteTestList[2], EmpleadoTestList[8]),
    Venta(4, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[1], ClienteTestList[3], EmpleadoTestList[19]),
    Venta(5, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[1], ClienteTestList[4], EmpleadoTestList[12]),
    Venta(6, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[1], ClienteTestList[5], EmpleadoTestList[3]),
    Venta(7, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[1], ClienteTestList[6], EmpleadoTestList[4]),
    Venta(8, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[2], ClienteTestList[7], EmpleadoTestList[10]),
    Venta(9, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[3], ClienteTestList[8], EmpleadoTestList[15]),
    Venta(10, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[4], ClienteTestList[9], EmpleadoTestList[17]),
    Venta(11, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[5], ClienteTestList[10], EmpleadoTestList[0]),
    Venta(12, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[6], ClienteTestList[11], EmpleadoTestList[5]),
    Venta(13, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[7], ClienteTestList[12], EmpleadoTestList[8]),
    Venta(14, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[8], ClienteTestList[13], EmpleadoTestList[19]),
    Venta(15, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[9], ClienteTestList[14], EmpleadoTestList[12]),
    Venta(16, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[10], ClienteTestList[15], EmpleadoTestList[3]),
    Venta(17, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[11], ClienteTestList[16], EmpleadoTestList[4]),
    Venta(18, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[12], ClienteTestList[17], EmpleadoTestList[10]),
    Venta(19, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[13], ClienteTestList[18], EmpleadoTestList[15]),
    Venta(20, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[14], ClienteTestList[19], EmpleadoTestList[17]),
    Venta(21, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[15], ClienteTestList[0], EmpleadoTestList[0]),
    Venta(22, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[15], ClienteTestList[1], EmpleadoTestList[5]),
    Venta(23, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[16], ClienteTestList[2], EmpleadoTestList[8]),
    Venta(24, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[16], ClienteTestList[3], EmpleadoTestList[19]),
    Venta(25, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[17], ClienteTestList[4], EmpleadoTestList[12]),
    Venta(26, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[17], ClienteTestList[5], EmpleadoTestList[3]),
    Venta(27, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[18], ClienteTestList[6], EmpleadoTestList[4]),
    Venta(28, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[19], ClienteTestList[7], EmpleadoTestList[10]),
    Venta(29, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[20], ClienteTestList[8], EmpleadoTestList[15]),
    Venta(30, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[21], ClienteTestList[9], EmpleadoTestList[17]),
    Venta(31, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[22], ClienteTestList[10], EmpleadoTestList[0]),
    Venta(32, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[23], ClienteTestList[11], EmpleadoTestList[5]),
    Venta(33, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[24], ClienteTestList[12], EmpleadoTestList[8]),
    Venta(34, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[25], ClienteTestList[13], EmpleadoTestList[19]),
    Venta(35, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[25], ClienteTestList[14], EmpleadoTestList[12]),
    Venta(36, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[26], ClienteTestList[15], EmpleadoTestList[3]),
    Venta(37, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[27], ClienteTestList[16], EmpleadoTestList[4]),
    Venta(38, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[28], ClienteTestList[17], EmpleadoTestList[10]),
    Venta(39, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[28], ClienteTestList[18], EmpleadoTestList[15]),
    Venta(40, 100.0, 16.0, 116.0, 0.0, 116.0, FechaVentaTestList[29], ClienteTestList[19], EmpleadoTestList[17])
)