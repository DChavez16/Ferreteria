package controller.proveedor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.producto.Producto
import model.producto.ProductoTestList
import model.proveedor.Proveedor
import model.proveedor.ProveedorTestList

class ProveedorController {
    private var _proveedorState = MutableStateFlow(ProveedorState())
    val proveedorState: StateFlow<ProveedorState> = _proveedorState.asStateFlow()

    init {
        getProveedorList()
        getProductoList()
    }

    /*
    *  Methods
    */
    /**
     * Retrieves a list of suppliers from the database
     */
    private fun getProveedorList() {
        // TODO Change this temporal line when the database is implemented
        _proveedorState.value.proveedorList = ProveedorTestList
    }

    /**
     * Retrieves a list of products from the database
     */
    private fun getProductoList() {
        // TODO Change this temporal line when the database is implemented
        _proveedorState.value.productosList = ProductoTestList
    }

    /**
     * Updates the current supplier
     * @param proveedor The current supplier selected to edit, can be empty
     */
    fun changeCurrentProveedor(proveedor: Proveedor = Proveedor()) {
        _proveedorState.update { currentState ->
            currentState.copy(currentProveedor = proveedor)
        }
    }

    /**
     * Adds a new supplier to the database
     * @param proveedor Supplier to be deleted from the database
     */
    fun createProveedor(proveedor: Proveedor) {
        // TODO Implement this method when the database is implemented
    }

    /**
     * Edits the current supplier from the database
     * @param proveedor Supplier to be edited in the database
     */
    fun updateProveedor(proveedor: Proveedor) {
        // TODO Implement this method when the database is implemented
    }

    /**
     * Deletes the current supplier
     * @param proveedor Supplier to be deleted from the database
     */
    fun deleteProveedor(proveedor: Proveedor) {
        // TODO Implement this method when the database is implemented
    }

    /**
     * Validates if the current supplier's content is not empty
     */
    fun proveedorIsNotEmpty() = with(_proveedorState.value.currentProveedor) {
        this.nombre.isNotEmpty() && this.productos.isNotEmpty() && with(this.contacto) {
            this.correo.isNotEmpty() && this.telefono.isNotEmpty() && with(this.direccion) {
                this.municipio.isNotEmpty() && this.colonia.isNotEmpty() && this.calle.isNotEmpty() && this.numero > 0 && this.codigoPostal.isNotEmpty()
            }
        }
    }

    /**
     * Clears the current supplier's content
     */
    fun clearProveedor() {
        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    nombre = "", productos = emptyList(), contacto = currentState.currentProveedor.contacto.copy(
                        correo = "", telefono = "", direccion = currentState.currentProveedor.contacto.direccion.copy(
                            municipio = "", colonia = "", calle = "", numero = 0, codigoPostal = ""
                        )
                    )
                )
            )
        }
    }

    /**
     * Updates the name of the supplier
     * @param newName New name of the suplier
     */
    fun updateSupplierName(newName: String) {
        _proveedorState.update { currentState ->
            currentState.copy(currentProveedor = currentState.currentProveedor.copy(nombre = newName))
        }
    }

    /**
     * Updates the email of the supplier
     * @param newEmail New email of the suplier
     */
    fun updateSupplierMail(newEmail: String) {
        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    contacto = currentState.currentProveedor.contacto.copy(correo = newEmail)
                )
            )
        }
    }

    /**
     * Updates the phone of the supplier
     * @param newPhone New phone of the suplier
     */
    fun updateSupplierPhone(newPhone: String) {
        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    contacto = currentState.currentProveedor.contacto.copy(telefono = newPhone)
                )
            )
        }
    }

    /**
     * Updates the town of the supplier
     * @param newTown New town of the suplier
     */
    fun updateSupplierTown(newTown: String) {
        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    contacto = currentState.currentProveedor.contacto.copy(
                        direccion = currentState.currentProveedor.contacto.direccion.copy(municipio = newTown)
                    )
                )
            )
        }
    }

    /**
     * Updates the neighborhood of the supplier
     * @param newNeighborhood New neighborhood of the suplier
     */
    fun updateSupplierNeighborhood(newNeighborhood: String) {
        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    contacto = currentState.currentProveedor.contacto.copy(
                        direccion = currentState.currentProveedor.contacto.direccion.copy(colonia = newNeighborhood)
                    )
                )
            )
        }
    }

    /**
     * Updates the street of the supplier
     * @param newStreet New street of the suplier
     */
    fun updateSupplierStreet(newStreet: String) {
        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    contacto = currentState.currentProveedor.contacto.copy(
                        direccion = currentState.currentProveedor.contacto.direccion.copy(calle = newStreet)
                    )
                )
            )
        }
    }

    /**
     * Updates the directionNumber of the supplier
     * @param newDirectionNumberString New direction number of the suplier as a string value to be converted to integer
     */
    fun updateSupplierDirectionNumber(newDirectionNumberString: String) {
        val newDirectionNumber: Int = try {
            if (newDirectionNumberString.toInt() < 0) 0 else newDirectionNumberString.toInt()
        } catch (_: NumberFormatException) {
            0
        }

        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    contacto = currentState.currentProveedor.contacto.copy(
                        direccion = currentState.currentProveedor.contacto.direccion.copy(numero = newDirectionNumber)
                    )
                )
            )
        }
    }

    /**
     * Updates the postal code of the supplier
     * @param newPostalCode New postal code of the suplier
     */
    fun updateSupplierPostalCode(newPostalCode: String) {
        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    contacto = currentState.currentProveedor.contacto.copy(
                        direccion = currentState.currentProveedor.contacto.direccion.copy(codigoPostal = newPostalCode)
                    )
                )
            )
        }
    }

    /**
     * Add a product to the list of products provided by the supplier
     * @param producto Product to be added to the list
     */
    fun addProductToPromotion(producto: Producto) {
        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    productos = currentState.currentProveedor.productos.addProducto(producto)
                )
            )
        }
    }
}


data class ProveedorState(
    var currentProveedor: Proveedor = Proveedor(),
    var proveedorList: List<Proveedor> = emptyList(),
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
