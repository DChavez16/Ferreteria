package controller.promocion

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.producto.Producto
import model.producto.ProductoDatabase
import model.promocion.Promocion
import model.promocion.PromocionDatabase

class PromocionController {
    private var _promocionState = MutableStateFlow(PromocionState())
    val promocionState: StateFlow<PromocionState> = _promocionState.asStateFlow()

    init {
        getPromocionList()
        getProductoList()
    }

    /*
    *  Methods
    */
    /**
     * Retrieves a list of promotions from the database
     */
    private fun getPromocionList() {
        _promocionState.value.promocionList = PromocionDatabase.getPromocionList()
    }

    /**
     * Retrieves the products of the promotion from the database
     * @param id ID of the Promotion which products are going to be retrieved
     */
    private fun getProductosPromocion(id: Int?) {
        var newList: List<Producto> = emptyList()

        id?.let {
            newList = PromocionDatabase.getProductosPromocion(id)
        }

        _promocionState.update { currentState ->
            currentState.copy(
                currentPromocion = currentState.currentPromocion.copy(
                    productos = newList
                )
            )
        }
    }

    /**
     * Retrieves a list of products from the database
     */
    private fun getProductoList() {
        val newList = ProductoDatabase.getProductList()

        _promocionState.update { currentState ->
            currentState.copy(productosList = newList)
        }

        filterProductoList()
    }

    /**
     * Filters the current list of products to show only those who don't have a promotion assigned, and have the current
     * promotion assigned but aren't on the current list of products of the promotion
     */
    private fun filterProductoList() {
        val filteredList = _promocionState.value.productosList.filter { producto ->
            (producto.promocion?.id == null  ||  producto.promocion?.id == _promocionState.value.currentPromocion.id)  &&
                    !_promocionState.value.currentPromocion.productos.any { it.id == producto.id }
        }

        _promocionState.update { currentState ->
            currentState.copy(filteredProductoList = filteredList)
        }
    }

    /**
     * Updates the current promotion
     * @param promocion The current promotion selected to edit, can be empty
     */
    fun changeCurrentPromocion(promocion: Promocion = Promocion()) {
        _promocionState.update { currentState ->
            currentState.copy(currentPromocion = promocion)
        }

        getProductosPromocion(_promocionState.value.currentPromocion.id)
        getProductoList()
    }

    /**
     * Adds a new promotion to the database
     * @param promocion Promocion to be deleted from the database
     */
    fun createPromocion(promocion: Promocion) {
        if (PromocionDatabase.insertPromocion(promocion)) getPromocionList()
    }

    /**
     * Edits the current promocion from the database
     * @param promocion Promocion to be edited in the database
     */
    fun updatePromocion(promocion: Promocion) {
        if (PromocionDatabase.updatePromocion(promocion)) getPromocionList()
    }

    /**
     * Deletes the current promocion
     * @param promocion Promocion to be deleted from the database
     */
    fun deletePromocion(promocion: Promocion) {
        if (PromocionDatabase.deletePromocion(promocion)) getPromocionList()
    }

    /**
     * Validates if the current promotion's content is not empty
     */
    fun promocionIsNotEmpty() = with(_promocionState.value.currentPromocion) {
        this.description.isNotEmpty() && this.descuento > 0.0
    }

    /**
     * Clears the current promotion's content
     */
    fun clearPromocion() {
        _promocionState.update { currentState ->
            currentState.copy(
                currentPromocion = currentState.currentPromocion.copy(
                    description = "", descuento = 0.0, disponibilidad = false, productos = emptyList()
                )
            )
        }
        filterProductoList()
    }

    /**
     * Updates the description of the promotion
     * @param newDescription New description of the promotion
     */
    fun updatePromotionDescription(newDescription: String) {
        _promocionState.update { currentState ->
            currentState.copy(
                currentPromocion = currentState.currentPromocion.copy(
                    description = newDescription
                )
            )
        }
    }

    /**
     * Updates the discount of the promotion
     * @param newDiscountString New description of the promotion
     */
    fun updatePromotionDiscount(newDiscountString: String) {
        val newDiscount: Double

        try {
            newDiscount = if (newDiscountString == "") 0.0
            else newDiscountString.toDouble()

            if (newDiscount in 0.0..100.0) {
                _promocionState.update { currentState ->
                    currentState.copy(
                        currentPromocion = currentState.currentPromocion.copy(
                            descuento = newDiscount / 100
                        )
                    )
                }
            }
        } catch (_: NumberFormatException) {
        }
    }

    /**
     * Updates the availability of the promotion
     * @param newAvailability New description of the promotion
     */
    fun updatePromotionAvailability(newAvailability: Boolean) {
        _promocionState.update { currentState ->
            currentState.copy(
                currentPromocion = currentState.currentPromocion.copy(
                    disponibilidad = newAvailability
                )
            )
        }
    }

    /**
     * Add a product to the list of products afected by the promotion
     * @param producto Product to be added to the list
     */
    fun addProductToPromotion(producto: Producto) {
        _promocionState.update { currentState ->
            currentState.copy(
                currentPromocion = currentState.currentPromocion.copy(
                    productos = currentState.currentPromocion.productos.addProducto(producto)
                )
            )
        }

        filterProductoList()
    }

    /**
     * Removes a product to the list of products afected by the promotion
     * @param productoID Product to be removed to the list
     */
    fun removeProductFromPromotion(productoID: Int) {
        _promocionState.update { currentState ->
            currentState.copy(
                currentPromocion = currentState.currentPromocion.copy(
                    productos = currentState.currentPromocion.productos.removeProducto(productoID)
                )
            )
        }

        filterProductoList()
    }
}

data class PromocionState(
    var currentPromocion: Promocion = Promocion(),
    var promocionList: List<Promocion> = emptyList(),
    var productosList: List<Producto> = emptyList(),
    var filteredProductoList: List<Producto> = emptyList()
)

/*
* Helper methods
*/
private fun List<Producto>.addProducto(producto: Producto): List<Producto> {
    val newList = this.toMutableList()

    // Validate if is already in the list, if so, update the element content
    if (!this.contains(producto)) {
        newList.add(producto)
    }

    return newList.toList()
}

private fun List<Producto>.removeProducto(removedProductoID: Int): List<Producto> {
    val newList = this.toMutableList()

    // Validate if is already in the list, if so, update the element content
    newList.removeIf { producto ->
        producto.id == removedProductoID
    }

    return newList.toList()
}