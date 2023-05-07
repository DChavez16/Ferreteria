package ui.sucursal

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import model.sucursal.Sucursal
import model.sucursal.SucursalTestList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SucursalScreen() {
    var currentSucursal by remember { mutableStateOf<Sucursal?>(null) }
    var currentScreen by remember { mutableStateOf(SucursalScreenCodes.LIST) }

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
                    SucursalList(sucursalList = SucursalTestList, onAddSucursalClick = {
                        currentSucursal = null
                        currentScreen = SucursalScreenCodes.INFO
                    }, onEditSucursalClick = { sucursal ->
                        currentSucursal = sucursal
                        currentScreen = SucursalScreenCodes.INFO
                    })
                }

                SucursalScreenCodes.INFO -> {
                    if (currentSucursal != null) {
                        SucursalInfoScreen(
                            editable = true,
                            onReturnButtonClick = { currentScreen = SucursalScreenCodes.LIST },
                            onMainButtonClick = {},
                            onDeleteButtonClick = {},
                            selectedSucursal = currentSucursal
                        )
                    } else {
                        SucursalInfoScreen(
                            editable = false,
                            onReturnButtonClick = { currentScreen = SucursalScreenCodes.LIST },
                            onMainButtonClick = {}
                        )
                    }
                }
            }
        }
    }
}

private enum class SucursalScreenCodes {
    LIST, INFO
}