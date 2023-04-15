package ui.producto

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import data.model.ProductoTestList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductoScreen() {
    var currentScreen by remember { mutableStateOf(ProductoScreenCodes.LIST) }

    Surface(color = MaterialTheme.colors.background) {
        /* Draws the corresponding screen depending on the current navigation option selected at the sidebar
               Uses a vertical slide in animation towards the top or bottom of the screen
               depending on the navigation option's position in the sidebar */
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { width -> width } + fadeIn() with
                            slideOutHorizontally { width -> -width } + fadeOut()
                } else {
                    slideInHorizontally { width -> -width } + fadeIn() with
                            slideOutHorizontally { width -> width } + fadeOut()
                }
            }
        ) {
            when (it) {
                ProductoScreenCodes.LIST -> ProductoList(productoList = ProductoTestList) {
                    currentScreen = ProductoScreenCodes.INFO
                }

                ProductoScreenCodes.INFO -> ProductoInfoScreen(onClick = { currentScreen = ProductoScreenCodes.LIST })
            }
        }
    }
}


enum class ProductoScreenCodes {
    LIST,
    INFO
}