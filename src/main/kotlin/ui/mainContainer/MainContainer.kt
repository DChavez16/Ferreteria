package ui.mainContainer

import androidx.compose.animation.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import controller.MainController
import data.util.NavigationOptions
import data.util.NavigationOptionsCodes
import data.util.UserType
import ui.cliente.ClienteScreen
import ui.empleado.EmpleadoScreen
import ui.home.HomeScreen
import ui.producto.ProductoScreen
import ui.promocion.PromocionScreen
import ui.proveedor.ProveedorScreen
import ui.reporte.ReporteScreen
import ui.sucursal.SucursalScreen
import ui.venta.VentaScreen


/**
 * MainContainer main composable
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContainer(closeProgram: () -> Unit) {
    val mainController = MainController()
    var userType by remember { mutableStateOf(UserType.ADMINISTRATOR) }

    // State variable that defines which screen is showing in the main container
    var currentMainContainerContent by remember { mutableStateOf(NavigationOptionsCodes.INICIO) }

    Column {
        // Draws the top bar of the program, it'll be static during all the program's lifecycle
        TopBar()
        Row {
            // Draws the navigation sidebar of the program, it'll be static during all the program's lifecycle
            NavigationSideBar(
                navigationOptionsList = NavigationOptions(userType).navigationList,
                userType = userType,
                closeProgram = closeProgram,
                selectedItem = currentMainContainerContent,
                onNavigationOptionClicked = { currentMainContainerContent = it },
                onUserInfoClicked = {
                    userType = if (userType == UserType.CASHIER) UserType.ADMINISTRATOR else UserType.CASHIER
                }
            )
            /* Draws the corresponding screen depending on the current navigation option selected at the sidebar
               Uses a vertical slide in animation towards the top or bottom of the screen
               depending on the navigation option's position in the sidebar */
            AnimatedContent(
                targetState = currentMainContainerContent,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }
                }
            ) {
                screenContainer(currentScreen = it)
            }
        }
    }
}

@Composable
private fun screenContainer(currentScreen: NavigationOptionsCodes) {
    when (currentScreen) {
        NavigationOptionsCodes.INICIO -> HomeScreen()
        NavigationOptionsCodes.VENTAS -> VentaScreen()
        NavigationOptionsCodes.PRODUCTO -> ProductoScreen()
        NavigationOptionsCodes.PROMOCION -> PromocionScreen()
        NavigationOptionsCodes.PROVEEDOR -> ProveedorScreen()
        NavigationOptionsCodes.REPORTE -> ReporteScreen()
        NavigationOptionsCodes.EMPLEADO -> EmpleadoScreen()
        NavigationOptionsCodes.SUCURSAL -> SucursalScreen()
        NavigationOptionsCodes.CLIENTE -> ClienteScreen()
    }
}


/*
Main container preview
*/
@Preview
@Composable
private fun MainContainerPreview() {
    MaterialTheme {
        MainContainer {}
    }
}