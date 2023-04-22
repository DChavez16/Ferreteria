package ui.empleado

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import data.model.Empleado
import data.model.EmpleadoTestList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EmpleadoScreen() {
    var currentEmpleado by remember { mutableStateOf<Empleado?>(null) }
    var currentScreen by remember { mutableStateOf(EmpleadoScreenCodes.LIST) }

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
                EmpleadoScreenCodes.LIST -> {
                    EmpleadoList(empleadoList = EmpleadoTestList, onAddEmpleadoClick = {
                        currentEmpleado = null
                        currentScreen = EmpleadoScreenCodes.INFO
                    }, onEditEmpleadoClick = { empleado ->
                        currentEmpleado = empleado
                        currentScreen = EmpleadoScreenCodes.INFO
                    }, onEmpleadoElementClick = { empleado ->
                        currentEmpleado = empleado
                        currentScreen = EmpleadoScreenCodes.EMPLEADO_VENTAS
                    })
                }

                EmpleadoScreenCodes.EMPLEADO_VENTAS -> {
                    VentaEmpleadoList(
                        empleado = currentEmpleado!!,
                        onReturnButtonClick = { currentScreen = EmpleadoScreenCodes.LIST })
                }

                EmpleadoScreenCodes.INFO -> {
                    if (currentEmpleado != null) {
                        EmpleadoInfoScreen(
                            editable = true,
                            onReturnButtonClick = { currentScreen = EmpleadoScreenCodes.LIST },
                            onMainButtonClick = {},
                            onDeleteButtonClick = {},
                            selectedEmpleado = currentEmpleado
                        )
                    }
                    else {
                        EmpleadoInfoScreen(
                            editable = false,
                            onReturnButtonClick = { currentScreen = EmpleadoScreenCodes.LIST },
                            onMainButtonClick = {}
                        )
                    }
                }
            }
        }
    }
}


private enum class EmpleadoScreenCodes {
    LIST, INFO, EMPLEADO_VENTAS
}