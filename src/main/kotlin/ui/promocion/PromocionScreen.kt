package ui.promocion

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import controller.promocion.PromocionController
import model.promocion.Promocion

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PromocionScreen() {
    val promocionController = PromocionController()

    var currentScreen by remember { mutableStateOf(PromocionScreenCodes.LIST) }

    val promocionState = promocionController.promocionState.collectAsState()

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
                PromocionScreenCodes.LIST -> {
                    PromocionList(promocionList = promocionState.value.promocionList, onAddPromocionClicked = {
                        promocionController.changeCurrentPromocion()
                        currentScreen = PromocionScreenCodes.INFO
                    }, onEditPromocionClicked = { promocion: Promocion ->
                        promocionController.changeCurrentPromocion(promocion)
                        currentScreen = PromocionScreenCodes.INFO
                    }, onPromocionClicked = { promocion: Promocion ->
                        promocionController.changeCurrentPromocion(promocion)
                        currentScreen = PromocionScreenCodes.PRODUCTO_PROMOCION
                    })
                }

                PromocionScreenCodes.PRODUCTO_PROMOCION -> {
                    ProductoPromocionList(promocion = promocionState.value.currentPromocion, onReturnButtonClick = {
                        currentScreen = PromocionScreenCodes.LIST
                    })
                }

                PromocionScreenCodes.INFO -> {
                    if (promocionState.value.currentPromocion.id != null) {
                        // Draws the promotion info screen on edit mode
                        PromocionInfoScreen(editPromocion = true,
                            promocionController = promocionController,
                            onReturnButtonClick = { currentScreen = PromocionScreenCodes.LIST },
                            onMainButtonClick = {
                                promocionController.updatePromocion(promocionState.value.currentPromocion)
                                currentScreen = PromocionScreenCodes.LIST
                            },
                            onDeleteClick = {
                                promocionController.deletePromocion(promocionState.value.currentPromocion)
                                currentScreen = PromocionScreenCodes.LIST
                            })
                    } else {
                        // Draws the promotion info screen on add mode
                        PromocionInfoScreen(editPromocion = false,
                            promocionController = promocionController,
                            onReturnButtonClick = { currentScreen = PromocionScreenCodes.LIST },
                            onMainButtonClick = {
                                promocionController.createPromocion(promocionState.value.currentPromocion)
                                currentScreen = PromocionScreenCodes.LIST
                            })
                    }
                }
            }
        }
    }
}

enum class PromocionScreenCodes {
    LIST, INFO, PRODUCTO_PROMOCION
}