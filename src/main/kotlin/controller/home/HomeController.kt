package controller.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.producto.Producto
import model.producto.ProductoTestList
import kotlin.random.Random

class HomeController {
    private var _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    var productsList: List<Producto>

    init {
        resetState()
        // TODO Change this temporal line when the database is implemented
        productsList = ProductoTestList
    }

    // Methods
    /**
     * Reinitialize the home state to default values
     * */
    fun resetState() {
        _homeState.value.selectedProductos = emptyList()
        _homeState.value.saleInfo = SaleInfo(0.0, 0.0, 0.0, 0.0)
    }

    /**
     * Adds the passed product and quantity into the list of selected products.
     * Then updates the sale info based in the product contained in the selected products list
     * @param producto The product that will be added to the list
     * @param quantity The quantity selected for an individual product
     */
    fun onAddProductoClick(producto: Producto, quantity: Int?) {
        _homeState.update { currentState ->
            currentState.copy(selectedProductos = currentState.selectedProductos.addProducto(producto, quantity!!))
        }
        updateSaleInfo(_homeState.value.selectedProductos)
    }

    private fun updateSaleInfo(selectedProductos: List<SelectedProductos>) {
        _homeState.value.saleInfo.update(selectedProductos)
    }

    /**
     * Validates if the list of selected products is not empty
     */
    fun selectedProductsListIsNotEmpty() = _homeState.value.selectedProductos.isNotEmpty()
}


/*
* Controller Data class
*/
data class HomeState(
    var selectedProductos: List<SelectedProductos> = emptyList(),
    var saleInfo: SaleInfo = SaleInfo(0.0, 0.0, 0.0, 0.0)
)

data class SelectedProductos(
    val producto: Producto, val cantidad: Int, val descuento: Double
)

data class SaleInfo(
    var subTotal: Double,
    var incrementoIVA: Double,
    var descuento: Double,
    var total: Double = subTotal - descuento
)

/*
Helper methods
*/
/**
 * Add a product and quantity into the selected products list.
 * If the product is already in the list, the list element where is located will update its quantity.
 * If the product is not in the list, the product will be added to the list
 */
private fun List<SelectedProductos>.addProducto(
    producto: Producto, quantityValue: Int
): List<SelectedProductos> {
    var isInTheList = false
    val newList: MutableList<SelectedProductos> = this.toMutableList()

    // Validate if is already in the list, if so, update the element content
    newList.forEachIndexed { index, selectedProductos ->
        if (producto.id == selectedProductos.producto.id) {
            isInTheList = true
            newList[index] = SelectedProductos(producto, quantityValue, getDescuento())
        }
    }

    if (!isInTheList) newList.add(SelectedProductos(producto, quantityValue, getDescuento()))

    return newList.toList()
}

private fun getDescuento() = if (Random.nextBoolean()) ListaDescuentos[(0..4).random()] else 0.0

/**
 * Navigates within selectedProductosList to calculate the SaleInfo's subtotal, descuento and total
 */
private fun SaleInfo.update(selectedProductosList: List<SelectedProductos>) {
    var newSubtotal = 0.0
    var newIVA = 0.0
    var newDescuento = 0.0

    selectedProductosList.forEach { selectedProducto ->
        newSubtotal += selectedProducto.producto.precioReal * selectedProducto.cantidad
        newIVA += (selectedProducto.producto.precioVenta - selectedProducto.producto.precioReal) * selectedProducto.cantidad
        newDescuento += selectedProducto.producto.precioReal * selectedProducto.cantidad * selectedProducto.descuento
    }

    this.subTotal = newSubtotal
    this.incrementoIVA = newIVA
    this.descuento = newDescuento
    this.total = newSubtotal + newIVA - newDescuento
}

private val ListaDescuentos = listOf(
    0.05, 0.10, 0.15, 0.20, 0.25
)