package ui.cliente

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import model.cliente.Cliente
import model.cliente.ClienteTestList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClienteScreen() {
    var currentCliente by remember { mutableStateOf<Cliente?>(null) }
    var currentScreen by remember { mutableStateOf(ClienteScreenCodes.LIST) }

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
                    ClienteList(clienteList = ClienteTestList, onAddClienteClick = {
                        currentCliente = null
                        currentScreen = ClienteScreenCodes.INFO
                    }, onEditClienteClick = { cliente ->
                        currentCliente = cliente
                        currentScreen = ClienteScreenCodes.INFO
                    }, onClienteElementClick = { cliente ->
                        currentCliente = cliente
                        currentScreen = ClienteScreenCodes.CLIENTE_COMPRAS
                    })
                }

                ClienteScreenCodes.CLIENTE_COMPRAS -> {
                    VentaClienteList(
                        selectedCliente = currentCliente!!,
                        onReturnButtonClick = {
                            currentScreen = ClienteScreenCodes.LIST
                        }
                    )
                }

                ClienteScreenCodes.INFO -> {
                    if (currentCliente != null) {
                        ClienteInfoScreen(
                            editable = true,
                            onReturnButtonClick = { currentScreen = ClienteScreenCodes.LIST },
                            onMainButtonClick = {},
                            onDeleteButtonClick = {},
                            selectedCliente = currentCliente
                        )
                    } else {
                        ClienteInfoScreen(
                            editable = false,
                            onReturnButtonClick = { currentScreen = ClienteScreenCodes.LIST },
                            onMainButtonClick = {}
                        )
                    }
                }
            }
        }
    }
}


private enum class ClienteScreenCodes {
    LIST, INFO, CLIENTE_COMPRAS
}