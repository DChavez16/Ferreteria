package ui.mainContainer

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import controller.MainController
import androidx.compose.runtime.Composable
import data.util.NavigationOptions
import data.util.UserType
import ui.home.HomeScreen


/**
 * MainContainer main composable
 */
@Composable
fun MainContainer(closeProgram: () -> Unit) {
    val mainController = MainController()

    Column {
        TopBar()
        Row {
            NavigationSideBar(NavigationOptions(UserType.ADMINISTRATOR).navigationList, closeProgram)
            HomeScreen()
        }
    }
}


/*
Main container preview
*/
@Preview
@Composable
fun MainContainerPreview() {
    MaterialTheme {
        MainContainer {}
    }
}

/* Program screens
// Home screen
HomeScreen()

// Venta screen
VentaScreen()

// Producto screen
ProductoScreen()

// Promocion screen
PromocionScreen()

// Proveedor screen
ProveedorScreen()

// Reporte screen
ReporteScreen()

// Empleado Screen
EmpleadoScreen()

// Sucursal screen
SucursalScreen()

// Cliente screen
ClienteScreen()
*/