package ui.producto

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import controller.producto.ProductoController
import model.producto.Producto

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductoScreen() {
    val productoController = ProductoController()

    var currentScreen by remember { mutableStateOf(ProductoScreenCodes.LIST) }

    val productoState = productoController.productoState.collectAsState()

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
                ProductoScreenCodes.LIST -> {
                    ProductoList(productoList = productoState.value.productsList, onAddProductoClicked = {
                        productoController.changeCurrentProduct()
                        currentScreen = ProductoScreenCodes.INFO
                    }, onEditProductoClicked = { producto: Producto ->
                        productoController.changeCurrentProduct(producto)
                        currentScreen = ProductoScreenCodes.INFO
                    })
                }

                ProductoScreenCodes.INFO -> {
                    if (productoState.value.currentProduct.id != null) {
                        // Draws the product info screen on edit mode
                        ProductoInfoScreen(editProduct = true,
                            productoController = productoController,
                            onReturnButtonClick = { currentScreen = ProductoScreenCodes.LIST },
                            onMainButtonClick = {
                                productoController.updateProducto(productoState.value.currentProduct)
                                currentScreen = ProductoScreenCodes.LIST
                            },
                            onDeleteClick = {
                                productoController.deleteProduct(productoState.value.currentProduct)
                                currentScreen = ProductoScreenCodes.LIST
                            })
                    } else {
                        // Draws the product info scree on add mode
                        ProductoInfoScreen(editProduct = false,
                            productoController = productoController,
                            onReturnButtonClick = { currentScreen = ProductoScreenCodes.LIST },
                            onMainButtonClick = {
                                productoController.createProduct(productoState.value.currentProduct)
                                currentScreen = ProductoScreenCodes.LIST
                            })
                    }
                }
            }
        }
    }
}


enum class ProductoScreenCodes {
    LIST, INFO
}