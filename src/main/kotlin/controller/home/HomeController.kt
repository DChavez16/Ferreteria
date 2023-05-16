package controller.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.cliente.Cliente
import model.cliente.ClienteDatabase
import model.producto.Producto
import model.producto.ProductoDatabase
import model.productoVenta.ProductoVenta

class HomeController {
    private var _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    var productsList: List<Producto> = emptyList()

    val clienteNamePair: MutableList<Pair<String, Cliente>> = mutableListOf()

    init {
        getClientList()
        getProductsList()
        resetState()
    }

    /**
     * Retrieves a list of clients from the database
     */
    private fun getClientList() {
        _homeState.value.clientList = ClienteDatabase.getClienteList()

        _homeState.value.clientList.forEach { cliente ->
            clienteNamePair.add(Pair(cliente.nombre, cliente))
        }
    }

    /**
     * Retrieves a list of products from the database
     */
    private fun getProductsList() {
        productsList = ProductoDatabase.getProductList()
    }

    // Methods
    /**
     * Reinitialize the home state to default values
     * */
    fun resetState() {
        _homeState.update { currentState ->
            currentState.copy(
                selectedProductos = emptyList(), saleInfo = SaleInfo(0.0, 0.0, 0.0, 0.0)
            )
        }
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

    private fun updateSaleInfo(selectedProductos: List<ProductoVenta>) {
        _homeState.value.saleInfo.update(selectedProductos)
    }

    fun makeSale() {

    }

    fun updateCurrentCliente(newClienteString: String) {
        val cliente = clienteNamePair.find { it.first == newClienteString }?.second ?: Cliente()

        _homeState.update { currentState ->
            currentState.copy(currentCliente = cliente)
        }
    }

    /**
     * Validates if the list of selected products is not empty
     */
    fun selectedProductsListIsNotEmpty() = _homeState.value.selectedProductos.isNotEmpty()
}

// Insertar SelectedProducto en la base de datos ProductoVenta
// Insertar Venta en la base de datos Venta
// Insertar el ID de SelectedProducto y el ID de Venta en la DetalleProductoVenta


/*
* Controller Data class
*/
data class HomeState(
    var selectedProductos: List<ProductoVenta> = emptyList(),
    var saleInfo: SaleInfo = SaleInfo(0.0, 0.0, 0.0, 0.0),
    var clientList: List<Cliente> = emptyList(),
    var currentCliente: Cliente = Cliente()
)

data class SaleInfo(
    var subTotal: Double, var incrementoIVA: Double, var descuento: Double, var total: Double = subTotal - descuento
)

/*
Helper methods
*/
/**
 * Add a product and quantity into the selected products list.
 * If the product is already in the list, the list element where is located will update its quantity.
 * If the product is not in the list, the product will be added to the list
 */
private fun List<ProductoVenta>.addProducto(
    producto: Producto, quantityValue: Int
): List<ProductoVenta> {
    var isInTheList = false
    val newList: MutableList<ProductoVenta> = this.toMutableList()

    // Validate if is already in the list, if so, update the element content
    newList.forEachIndexed { index, selectedProductos ->
        if (producto.id == selectedProductos.producto.id) {
            isInTheList = true
            newList[index] = ProductoVenta(cantidad = quantityValue, producto = producto)
        }
    }

    if (!isInTheList) newList.add(ProductoVenta(cantidad = quantityValue, producto = producto))

    return newList.toList()
}

/**
 * Navigates within selectedProductosList to calculate the SaleInfo's subtotal, descuento and total
 */
private fun SaleInfo.update(selectedProductosList: List<ProductoVenta>) {
    var newSubtotal = 0.0
    var newIVA = 0.0
    var newDescuento = 0.0

    selectedProductosList.forEach { selectedProducto ->
        newSubtotal += selectedProducto.subtotal
        newIVA += selectedProducto.cantidadIVA
        if(selectedProducto.promocion?.disponibilidad == true) {
            newDescuento += (selectedProducto.subtotal + selectedProducto.cantidadIVA) * (selectedProducto.promocion?.descuento
                ?: 0.0)
        }
    }

    this.subTotal = newSubtotal
    this.incrementoIVA = newIVA
    this.descuento = newDescuento
    this.total = newSubtotal + newIVA - newDescuento
}