package ui.cliente

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import controller.cliente.ClienteController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClienteScreen() {
    val clienteController = ClienteController()

    var currentScreen by remember { mutableStateOf(ClienteScreenCodes.LIST) }

    val clienteState = clienteController.clienteState.collectAsState()

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
                ClienteScreenCodes.LIST -> {
                    ClienteList(clienteList = clienteState.value.clienteList, onAddClienteClick = {
                        clienteController.changeCurrentCliente()
                        currentScreen = ClienteScreenCodes.INFO
                    }, onEditClienteClick = { cliente ->
                        clienteController.changeCurrentCliente(cliente)
                        currentScreen = ClienteScreenCodes.INFO
                    }, onClienteElementClick = { cliente ->
                        clienteController.changeCurrentCliente(cliente)
                        currentScreen = ClienteScreenCodes.CLIENTE_COMPRAS
                    })
                }

                ClienteScreenCodes.CLIENTE_COMPRAS -> {
                    VentaClienteList(selectedCliente = clienteState.value.currentCliente, onReturnButtonClick = {
                        currentScreen = ClienteScreenCodes.LIST
                    })
                }

                ClienteScreenCodes.INFO -> {
                    if (clienteState.value.currentCliente.id != null) {
                        ClienteInfoScreen(editable = true,
                            clienteController = clienteController,
                            onReturnButtonClick = { currentScreen = ClienteScreenCodes.LIST },
                            onMainButtonClick = {
                                clienteController.updateCliente(clienteState.value.currentCliente)
                                currentScreen = ClienteScreenCodes.LIST
                            },
                            onDeleteButtonClick = {
                                clienteController.deleteCliente(clienteState.value.currentCliente)
                                currentScreen = ClienteScreenCodes.LIST
                            })
                    } else {
                        ClienteInfoScreen(editable = false,
                            clienteController = clienteController,
                            onReturnButtonClick = { currentScreen = ClienteScreenCodes.LIST },
                            onMainButtonClick = {
                                clienteController.createCliente(clienteState.value.currentCliente)
                                currentScreen = ClienteScreenCodes.LIST
                            })
                    }
                }
            }
        }
    }
}


private enum class ClienteScreenCodes {
    LIST, INFO, CLIENTE_COMPRAS
}