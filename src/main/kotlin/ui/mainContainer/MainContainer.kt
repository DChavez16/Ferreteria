package ui.mainContainer

import controller.MainController
import androidx.compose.runtime.Composable
import ui.cliente.ClienteScreen
import ui.empleado.EmpleadoScreen
import ui.home.MakeVentaScreen
import ui.producto.ProductoScreen
import ui.promocion.PromocionScreen
import ui.proveedor.ProveedorScreen
import ui.reporte.ReporteScreen
import ui.sucursal.SucursalScreen
import ui.venta.VentaScreen


/**
 * MainContainer main composable
 */
@Composable
fun MainContainer() {
    val mainController = MainController()

    // Proveedor screen
    ProveedorScreen()

    // Producto screen
    ProductoScreen()

    // Promocion screen
    PromocionScreen()

    // Venta screen
    VentaScreen()

    // Reporte screen
    ReporteScreen()

    // Empleado Screen
    EmpleadoScreen()

    // Sucursal screen
    SucursalScreen()

    // Cliente screen
    ClienteScreen()
}