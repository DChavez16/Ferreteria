package controller.proveedor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.producto.Producto
import model.proveedor.Proveedor
import model.proveedor.ProveedorDatabase
import util.isValidEmail
import util.isValidPhoneNumber
import util.isValidPostalCode

class ProveedorController {
    private var _proveedorState = MutableStateFlow(ProveedorState())
    val proveedorState: StateFlow<ProveedorState> = _proveedorState.asStateFlow()

    init {
        getProveedorList()
    }

    /*
    *  Methods
    */
    /**
     * Retrieves a list of suppliers from the database
     */
    private fun getProveedorList() {
        _proveedorState.value.proveedorList = ProveedorDatabase.getProveedorList()
    }

    /**
     * Retrieves a list of products provided by the supplir from the database
     * @param id ID of the Proveedor which products are going to be retrieved
     */
    private fun getProductosProveedor(id: Int?) {
        var newList: List<Producto> = emptyList()

        id?.let {
            newList = ProveedorDatabase.getProductosProveedor(id)
        }

        _proveedorState.update { currentState ->
            currentState.copy(
                currentProveedor = currentState.currentProveedor.copy(
                    productos = newList
                )
            )
        }
    }

    /**
     * Updates the current supplier
     * @param proveedor The current supplier selected to edit, can be empty
     */
    fun changeCurrentProveedor(proveedor: Proveedor = Proveedor()) {
        _proveedorState.update { currentState ->
            currentState.copy(currentProveedor = proveedor)
        }

        getProductosProveedor(_proveedorState.value.currentProveedor.id)
    }

    /**
     * Adds a new supplier to the database
     * @param proveedor Supplier to be deleted from the database
     */
    fun createProveedor(proveedor: Proveedor) {
        if (ProveedorDatabase.insertProveedor(proveedor)) getProveedorList()
    }

    /**
     * Edits the current supplier from the database
     * @param proveedor Supplier to be edited in the database
     */
    fun updateProveedor(proveedor: Proveedor) {
        if (ProveedorDatabase.updateProveedor(proveedor)) getProveedorList()
    }

    /**
     * Deletes the current supplier
     * @param proveedor Supplier to be deleted from the database
     */
    fun deleteProveedor(proveedor: Proveedor) {
        if (ProveedorDatabase.deleteProveedor(proveedor)) getProveedorList()
    }

    /**
     * Validates if the current supplier's content is not empty
     */
    fun proveedorIsNotEmpty() = with(_proveedorState.value.currentProveedor) {
        this.nombre.isNotEmpty() && with(this.contacto) {
            this.correo.isValidEmail() && this.telefono.isValidPhoneNumber() && with(this.direccion) {
                this.municipio.isNotEmpty() && this.colonia.isNotEmpty() && this.calle.isNotEmpty() && this.numero > 0 && this.codigoPostal.isValidPostalCode()
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
                    nombre = "", contacto = currentState.currentProveedor.contacto.copy(
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
}


data class ProveedorState(
    var currentProveedor: Proveedor = Proveedor(), var proveedorList: List<Proveedor> = emptyList()
)
