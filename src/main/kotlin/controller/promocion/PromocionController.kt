package controller.promocion

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.producto.Producto
import model.promocion.Promocion

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
        // TODO Change this temporal line when the database is implemented
    }

    /**
     * Retrieves a list of products from the database
     */
    private fun getProductoList() {
        // TODO Change this temporal line when the database is implemented
    }

    /**
     * Updates the current promotion
     * @param promocion The current promotion selected to edit, can be empty
     */
    fun changeCurrentPromocion(promocion: Promocion = Promocion()) {
        _promocionState.update { currentState ->
            currentState.copy(currentPromocion = promocion)
        }
    }

    /**
     * Adds a new promotion to the database
     * @param promocion Promocion to be deleted from the database
     */
    fun createPromocion(promocion: Promocion) {
        // TODO Implement this method when the database is implemented
    }

    /**
     * Edits the current promocion from the database
     * @param promocion Promocion to be edited in the database
     */
    fun updatePromocion(promocion: Promocion) {
        // TODO Implement this method when the database is implemented
    }

    /**
     * Deletes the current promocion
     * @param promocion Promocion to be deleted from the database
     */
    fun deletePromocion(promocion: Promocion) {
        // TODO Implement this method when the database is implemented
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

            if(newDiscount in 0.0..100.0) {
                _promocionState.update { currentState ->
                    currentState.copy(
                        currentPromocion = currentState.currentPromocion.copy(
                            descuento = newDiscount/100
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
    }
}

data class PromocionState(
    var currentPromocion: Promocion = Promocion(),
    var promocionList: List<Promocion> = emptyList(),
    var productosList: List<Producto> = emptyList()
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