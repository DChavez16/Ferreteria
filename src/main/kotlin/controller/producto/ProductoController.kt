package controller.producto

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.producto.Producto
import model.producto.ProductoDatabase
import model.proveedor.Proveedor
import model.proveedor.ProveedorDatabase

class ProductoController {
    private var _productoState = MutableStateFlow(ProductoState())
    val productoState: StateFlow<ProductoState> = _productoState.asStateFlow()

    val proveedorNamePair: MutableList<Pair<String, Proveedor>> = mutableListOf()

    init {
        getProductsList()
        getProveedorList()
    }

    /*
    *  Methods
    */
    /**
     * Retrieves a list of products from the database
     */
    private fun getProductsList() {
        _productoState.value.productsList = ProductoDatabase.getProductList()
    }

    /**
     * Retrieves a list of suppliers from the database
     */
    private fun getProveedorList() {
        _productoState.value.proveedorList = ProveedorDatabase.getProveedorList()

        _productoState.value.proveedorList.forEach { proveedor ->
            proveedorNamePair.add(Pair(proveedor.nombre, proveedor))
        }
    }

    /**
     * Updates the current product
     * @param producto The current product selected to edit, can be null
     */
    fun changeCurrentProduct(producto: Producto = Producto()) {
        _productoState.update { currentState ->
            currentState.copy(currentProduct = producto)
        }
    }

    /**
     * Adds a new product to the database
     * @param producto Product to be deleted from the database
     */
    fun createProduct(producto: Producto) {
        if(ProductoDatabase.insertProducto(producto)) getProductsList()
    }

    /**
     * Edits the current product from the database
     * @param producto Producto to be edited in the database
     */
    fun updateProducto(producto: Producto) {
        if(ProductoDatabase.updateProducto(producto)) getProductsList()
    }

    /**
     * Deletes the current product
     * @param producto Product to be deleted from the database
     */
    fun deleteProduct(producto: Producto) {
        if(ProductoDatabase.deleteProducto(producto)) getProductsList()
    }

    /**
     * Clears the current product's content
     */
    fun clearProduct() {
        _productoState.update { currentState ->
            currentState.copy(
                currentProduct = currentState.currentProduct.copy(
                    nombre = "",
                    precioReal = 0.0,
                    cantidadIVA = 0.0,
                    precioVenta = 0.0,
                    descripcion = "",
                    proveedor = Proveedor()
                )
            )
        }
    }

    /**
     * Validates if the current product's content is not empty
     */
    fun productoIsNotEmpty() = with(_productoState.value.currentProduct) {
        this.nombre.isNotEmpty() && this.precioVenta > 0.0 && this.descripcion.isNotEmpty() && this.proveedor.id != null
    }

    /**
     * Updates the name of the current product
     * @param newName New value that is going to be asigned as product's name
     */
    fun updateProductName(newName: String) {
        _productoState.update { currentState ->
            currentState.copy(currentProduct = currentState.currentProduct.copy(nombre = newName))
        }
    }

    /**
     * Updates the description of the current product
     */
    fun updateProductDescription(newDescription: String) {
        _productoState.update { currentState ->
            currentState.copy(currentProduct = currentState.currentProduct.copy(descripcion = newDescription))
        }
    }

    /**
     * Updates the sell price of the current product
     * @param newSellPrice Value that is going to be asigned as the product's sell price
     */
    fun updateSellPrice(newSellPrice: String) {
        try {
            if (newSellPrice.length <= 9 && newSellPrice.toDouble() in 0.0..100000.0) {
                _productoState.update { currentState ->
                    currentState.copy(currentProduct = currentState.currentProduct.copy(precioVenta = newSellPrice.toDouble()))
                }
                updateRealPrice(_productoState.value.currentProduct.precioVenta)
            }
        } catch (_: NumberFormatException) {
        }
    }

    /**
     * Updates the IVA price increase based on the provided sell price
     * @param sellPrice base sell price which the IVA price increase will be calculated
     */
    private fun updateRealPrice(sellPrice: Double) {
        _productoState.update { currentState ->
            currentState.copy(currentProduct = currentState.currentProduct.copy(precioReal = (sellPrice / 1.16)))
        }
        updateIVAIncrease()
    }

    /**
     * Updates the real price increase
     */
    private fun updateIVAIncrease() {
        val newIVAIncrease = with(_productoState.value.currentProduct) {
            this.precioVenta - this.precioReal
        }
        _productoState.update { currentState ->
            currentState.copy(currentProduct = currentState.currentProduct.copy(cantidadIVA = newIVAIncrease))
        }
    }

    /**
     * Updates the supplier of the product
     * @param newSupplierString New supplier of the producto
     */
    fun updateSupplier(newSupplierString: String) {
        val proveedor = proveedorNamePair.find { it.first == newSupplierString }?.second ?: Proveedor()

        _productoState.update { currentState ->
            currentState.copy(currentProduct = currentState.currentProduct.copy(proveedor = proveedor))
        }
    }
}


/*
* Controller data class
*/
data class ProductoState(
    var currentProduct: Producto = Producto(),
    var productsList: List<Producto> = emptyList(),
    var proveedorList: List<Proveedor> = emptyList()
)