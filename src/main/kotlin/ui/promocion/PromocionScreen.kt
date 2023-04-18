package ui.promocion

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import data.model.Promocion
import data.model.PromocionTestList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PromocionScreen() {
    var currentScreen by remember { mutableStateOf(PromocionScreenCodes.LIST) }
    var currentPromocion by remember { mutableStateOf<Promocion?>(null) }

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
                    PromocionList(promocionList = PromocionTestList, onAddPromocionClicked = {
                        currentPromocion = null
                        currentScreen = PromocionScreenCodes.INFO
                    }, onEditPromocionClicked = { promocion: Promocion ->
                        currentPromocion = promocion
                        currentScreen = PromocionScreenCodes.INFO
                    }) { promocion: Promocion ->
                        currentPromocion = promocion
                        currentScreen = PromocionScreenCodes.PRODUCTO_PROMOCION
                    }
                }

                PromocionScreenCodes.PRODUCTO_PROMOCION -> {
                    ProductoPromocionList(promocion = currentPromocion!!, onReturnButtonClick = {
                        currentScreen = PromocionScreenCodes.LIST
                    })
                }

                PromocionScreenCodes.INFO -> {
                    if (currentPromocion != null) {
                        // Draws the promotion info screen on edit mode
                        PromocionInfoScreen(editPromocion = true,
                            onReturnButtonClick = { currentScreen = PromocionScreenCodes.LIST },
                            onMainButtonClick = {},
                            promocion = currentPromocion,
                            onDeleteClick = {})
                    } else {
                        // Draws the promotion info screen on add mode
                        PromocionInfoScreen(editPromocion = false,
                            onReturnButtonClick = { currentScreen = PromocionScreenCodes.LIST },
                            onMainButtonClick = {})
                    }
                }
            }
        }
    }
}

enum class PromocionScreenCodes {
    LIST, INFO, PRODUCTO_PROMOCION
}