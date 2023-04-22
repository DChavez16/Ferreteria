package ui.proveedor

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import data.model.Proveedor
import data.model.ProveedorTestList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProveedorScreen() {
    var currentScreen by remember { mutableStateOf(ProveedorScreenCodes.LIST) }
    var currentProveedor by remember { mutableStateOf<Proveedor?>(null) }

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
                    ProveedorList(proveedorList = ProveedorTestList, onAddClicked = {
                        currentScreen = ProveedorScreenCodes.INFO
                        currentProveedor = null
                    }, onEditClicked = { selectedProveedor ->
                        currentScreen = ProveedorScreenCodes.INFO
                        currentProveedor = selectedProveedor
                    }, onElementClicked = { selectedProveedor ->
                        currentScreen = ProveedorScreenCodes.PROVEEDOR_PRODUCTOS
                        currentProveedor = selectedProveedor
                    })
                }

                ProveedorScreenCodes.PROVEEDOR_PRODUCTOS -> {
                    ProductosProveedorList(selectedProveedor = currentProveedor!!,
                        onReturnButtonClick = { currentScreen = ProveedorScreenCodes.LIST })
                }

                ProveedorScreenCodes.INFO -> {
                    if (currentProveedor != null) {
                        // Draws the proveedor info screen on edit mode
                        ProveedorInfoScreen(editable = true,
                            onReturnButtonClick = { currentScreen = ProveedorScreenCodes.LIST },
                            onMainButtonClick = {},
                            onDeleteClick = {},
                            selectedProveedor = currentProveedor
                        )
                    } else {
                        // Draws the proveedor info screen on add mode
                        ProveedorInfoScreen(editable = false,
                            onReturnButtonClick = { currentScreen = ProveedorScreenCodes.LIST },
                            onMainButtonClick = {})
                    }
                }
            }
        }
    }
}


private enum class ProveedorScreenCodes {
    LIST, INFO, PROVEEDOR_PRODUCTOS
}