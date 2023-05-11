package ui.empleado

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import controller.empleado.EmpleadoController
import model.empleado.EmpleadoTestList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EmpleadoScreen() {
    val empleadoController = EmpleadoController()

    var currentScreen by remember { mutableStateOf(EmpleadoScreenCodes.LIST) }

    val empleadoState = empleadoController.empleadoState.collectAsState()

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
                        empleadoController.changeCurrentEmpleado()
                        currentScreen = EmpleadoScreenCodes.INFO
                    }, onEditEmpleadoClick = { empleado ->
                        empleadoController.changeCurrentEmpleado(empleado)
                        currentScreen = EmpleadoScreenCodes.INFO
                    }, onEmpleadoElementClick = { empleado ->
                        empleadoController.changeCurrentEmpleado(empleado)
                        currentScreen = EmpleadoScreenCodes.EMPLEADO_VENTAS
                    })
                }

                EmpleadoScreenCodes.EMPLEADO_VENTAS -> {
                    VentaEmpleadoList(empleado = empleadoState.value.currentEmpleado,
                        detalleVentaEmpleadoList = empleadoState.value.detalleVentasEmpleado,
                        onReturnButtonClick = { currentScreen = EmpleadoScreenCodes.LIST })
                }

                EmpleadoScreenCodes.INFO -> {
                    if (empleadoState.value.currentEmpleado.id != null) {
                        EmpleadoInfoScreen(editable = true,
                            empleadoController = empleadoController,
                            onReturnButtonClick = { currentScreen = EmpleadoScreenCodes.LIST },
                            onMainButtonClick = {
                                empleadoController.updateEmpleado(empleadoState.value.currentEmpleado)
                                currentScreen = EmpleadoScreenCodes.LIST
                            },
                            onDeleteButtonClick = {
                                empleadoController.deleteEmpleado(empleadoState.value.currentEmpleado)
                                currentScreen = EmpleadoScreenCodes.LIST
                            })
                    } else {
                        EmpleadoInfoScreen(editable = false,
                            empleadoController = empleadoController,
                            onReturnButtonClick = { currentScreen = EmpleadoScreenCodes.LIST },
                            onMainButtonClick = {
                                empleadoController.createEmpleado(empleadoState.value.currentEmpleado)
                                currentScreen = EmpleadoScreenCodes.LIST
                            })
                    }
                }
            }
        }
    }
}


private enum class EmpleadoScreenCodes {
    LIST, INFO, EMPLEADO_VENTAS
}