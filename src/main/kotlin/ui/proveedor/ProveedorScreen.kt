package ui.proveedor

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import controller.proveedor.ProveedorController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProveedorScreen() {
    val proveedorController = ProveedorController()

    var currentScreen by remember { mutableStateOf(ProveedorScreenCodes.LIST) }

    val proveedorState = proveedorController.proveedorState.collectAsState()

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
                ProveedorScreenCodes.LIST -> {
                    ProveedorList(proveedorList = proveedorState.value.proveedorList, onAddProveedorClick = {
                        currentScreen = ProveedorScreenCodes.INFO
                        proveedorController.changeCurrentProveedor()
                    }, onEdirtProveedorClick = { selectedProveedor ->
                        currentScreen = ProveedorScreenCodes.INFO
                        proveedorController.changeCurrentProveedor(selectedProveedor)
                    }, onProveedorElementClick = { selectedProveedor ->
                        currentScreen = ProveedorScreenCodes.PROVEEDOR_PRODUCTOS
                        proveedorController.changeCurrentProveedor(selectedProveedor)
                    })
                }

                ProveedorScreenCodes.PROVEEDOR_PRODUCTOS -> {
                    ProductosProveedorList(selectedProveedor = proveedorState.value.currentProveedor,
                        onReturnButtonClick = { currentScreen = ProveedorScreenCodes.LIST })
                }

                ProveedorScreenCodes.INFO -> {
                    if (proveedorState.value.currentProveedor.id != null) {
                        // Draws the proveedor info screen on edit mode
                        ProveedorInfoScreen(editable = true,
                            proveedorController = proveedorController,
                            onReturnButtonClick = { currentScreen = ProveedorScreenCodes.LIST },
                            onMainButtonClick = {
                                proveedorController.updateProveedor(proveedorState.value.currentProveedor)
                                currentScreen = ProveedorScreenCodes.LIST
                            },
                            onDeleteClick = {
                                proveedorController.deleteProveedor(proveedorState.value.currentProveedor)
                                currentScreen = ProveedorScreenCodes.LIST
                            })
                    } else {
                        // Draws the proveedor info screen on add mode
                        ProveedorInfoScreen(editable = false,
                            proveedorController = proveedorController,
                            onReturnButtonClick = { currentScreen = ProveedorScreenCodes.LIST },
                            onMainButtonClick = {
                                proveedorController.createProveedor(proveedorState.value.currentProveedor)
                                currentScreen = ProveedorScreenCodes.LIST
                            })
                    }
                }
            }
        }
    }
}


private enum class ProveedorScreenCodes {
    LIST, INFO, PROVEEDOR_PRODUCTOS
}