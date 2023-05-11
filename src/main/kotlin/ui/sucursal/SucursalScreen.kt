package ui.sucursal

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import controller.sucursal.SucursalController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SucursalScreen() {
    val sucursalController = SucursalController()

    var currentScreen by remember { mutableStateOf(SucursalScreenCodes.LIST) }

    val sucursalState = sucursalController.sucursalState.collectAsState()

    Surface(color = MaterialTheme.colors.background) {
        /* Draws the corresponding screen depending on the current navigation option selected at the sidebar
               Uses a vertical slide in animation towards the top or bottom of the screen
               depending on the navigation option's position in the sidebar */
        AnimatedContent(targetState = currentScreen, transitionSpec = {
            if (targetState > initialState) {
                slideInHorizontally { width -> width } + fadeIn() with slideOutHorizontally { width -> -width } + fadeOut()
            } else {
                slideInHorizontally { width -> -width } + fadeIn() with slideOutHorizontally { width -> width } + fadeOut()
            }
        }) {
            when (it) {
                SucursalScreenCodes.LIST -> {
                    SucursalList(sucursalList = sucursalState.value.sucursalList, onAddSucursalClick = {
                        sucursalController.changeCurrentSucursal()
                        currentScreen = SucursalScreenCodes.INFO
                    }, onEditSucursalClick = { sucursal ->
                        sucursalController.changeCurrentSucursal(sucursal)
                        currentScreen = SucursalScreenCodes.INFO
                    })
                }

                SucursalScreenCodes.INFO -> {
                    if (sucursalState.value.currentSucursal.id != null) {
                        SucursalInfoScreen(editable = true,
                            sucursalController = sucursalController,
                            onReturnButtonClick = { currentScreen = SucursalScreenCodes.LIST },
                            onMainButtonClick = {
                                sucursalController.updateSucursal(sucursalState.value.currentSucursal)
                                currentScreen = SucursalScreenCodes.LIST
                            },
                            onDeleteButtonClick = {
                                sucursalController.deleteSucursal(sucursalState.value.currentSucursal)
                                currentScreen = SucursalScreenCodes.LIST
                            })
                    } else {
                        SucursalInfoScreen(editable = false,
                            sucursalController = sucursalController,
                            onReturnButtonClick = { currentScreen = SucursalScreenCodes.LIST },
                            onMainButtonClick = {
                                sucursalController.createSucursal(sucursalState.value.currentSucursal)
                                currentScreen = SucursalScreenCodes.LIST
                            })
                    }
                }
            }
        }
    }
}

private enum class SucursalScreenCodes {
    LIST, INFO
}