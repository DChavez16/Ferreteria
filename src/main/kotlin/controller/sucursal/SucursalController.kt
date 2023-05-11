package controller.sucursal

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.sucursal.Sucursal
import model.sucursal.SucursalTestList

class SucursalController {
    private var _sucursalState = MutableStateFlow(SucursalState())
    val sucursalState: StateFlow<SucursalState> = _sucursalState.asStateFlow()

    init {
        getSucursalList()
    }

    /*
    *  Methods
    */
    /**
     * Retrieves a list of branches from the database
     */
    private fun getSucursalList() {
        // TODO Change this temporal line when the database is implemented
        _sucursalState.value.sucursalList = SucursalTestList
    }

    /**
     * Updates the current branch
     * @param sucursal The current branch selected to edit, can be empty
     */
    fun changeCurrentSucursal(sucursal: Sucursal = Sucursal()) {
        _sucursalState.update { currentState ->
            currentState.copy(currentSucursal = sucursal)
        }
    }

    /**
     * Adds a new branch to the database
     * @param sucursal Branch to be deleted from the database
     */
    fun createSucursal(sucursal: Sucursal) {
        // TODO Implement this method when the database is implemented
    }

    /**
     * Edits the current branch from the database
     * @param sucursal Branch to be edited in the database
     */
    fun updateSucursal(sucursal: Sucursal) {
        // TODO Implement this method when the database is implemented
    }

    /**
     * Deletes the current branch
     * @param sucursal Branch to be deleted from the database
     */
    fun deleteSucursal(sucursal: Sucursal) {
        // TODO Implement this method when the database is implemented
    }

    /**
     * Validates if the current branch's content is not empty
     */
    fun sucursalIsNotEmpty() = with(_sucursalState.value.currentSucursal) {
        this.name.isNotEmpty() && with(this.contacto) {
            this.telefono.isNotEmpty() && with(this.direccion) {
                this.municipio.isNotEmpty() && this.colonia.isNotEmpty() && this.calle.isNotEmpty() && this.numero > 0 && this.codigoPostal.isNotEmpty()
            }
        }
    }

    /**
     * Clears the current branch's content
     */
    fun clearSucursal() {
        _sucursalState.update { currentState ->
            currentState.copy(
                currentSucursal = currentState.currentSucursal.copy(
                    name = "", contacto = currentState.currentSucursal.contacto.copy(
                        telefono = "", direccion = currentState.currentSucursal.contacto.direccion.copy(
                            municipio = "", colonia = "", calle = "", numero = 0, codigoPostal = ""
                        )
                    )
                )
            )
        }
    }

    /**
     * Updates the name of the branch
     * @param newName New name of the branch
     */
    fun updateBranchName(newName: String) {
        _sucursalState.update { currentState ->
            currentState.copy(currentSucursal = currentState.currentSucursal.copy(name = newName))
        }
    }

    /**
     * Updates the phone of the branch
     * @param newPhone New phone of the branch
     */
    fun updateBranchPhone(newPhone: String) {
        _sucursalState.update { currentState ->
            currentState.copy(
                currentSucursal = currentState.currentSucursal.copy(
                    contacto = currentState.currentSucursal.contacto.copy(telefono = newPhone)
                )
            )
        }
    }

    /**
     * Updates the town of the branch
     * @param newTown New town of the branch
     */
    fun updateBranchTown(newTown: String) {
        _sucursalState.update { currentState ->
            currentState.copy(
                currentSucursal = currentState.currentSucursal.copy(
                    contacto = currentState.currentSucursal.contacto.copy(
                        direccion = currentState.currentSucursal.contacto.direccion.copy(municipio = newTown)
                    )
                )
            )
        }
    }

    /**
     * Updates the neighborhood of the branch
     * @param newNeighborhood New neighborhood of the branch
     */
    fun updateBranchNeighborhood(newNeighborhood: String) {
        _sucursalState.update { currentState ->
            currentState.copy(
                currentSucursal = currentState.currentSucursal.copy(
                    contacto = currentState.currentSucursal.contacto.copy(
                        direccion = currentState.currentSucursal.contacto.direccion.copy(colonia = newNeighborhood)
                    )
                )
            )
        }
    }

    /**
     * Updates the street of the branch
     * @param newStreet New street of the branch
     */
    fun updateBranchStreet(newStreet: String) {
        _sucursalState.update { currentState ->
            currentState.copy(
                currentSucursal = currentState.currentSucursal.copy(
                    contacto = currentState.currentSucursal.contacto.copy(
                        direccion = currentState.currentSucursal.contacto.direccion.copy(calle = newStreet)
                    )
                )
            )
        }
    }

    /**
     * Updates the directionNumber of the branch
     * @param newDirectionNumberString New direction number of the branch as a string value to be converted to integer
     */
    fun updateBranchDirectionNumber(newDirectionNumberString: String) {
        val newDirectionNumber: Int = try {
            if (newDirectionNumberString.toInt() < 0) 0 else newDirectionNumberString.toInt()
        } catch (_: NumberFormatException) {
            0
        }

        _sucursalState.update { currentState ->
            currentState.copy(
                currentSucursal = currentState.currentSucursal.copy(
                    contacto = currentState.currentSucursal.contacto.copy(
                        direccion = currentState.currentSucursal.contacto.direccion.copy(numero = newDirectionNumber)
                    )
                )
            )
        }
    }

    /**
     * Updates the postal code of the branch
     * @param newPostalCode New postal code of the branch
     */
    fun updateBranchPostalCode(newPostalCode: String) {
        _sucursalState.update { currentState ->
            currentState.copy(
                currentSucursal = currentState.currentSucursal.copy(
                    contacto = currentState.currentSucursal.contacto.copy(
                        direccion = currentState.currentSucursal.contacto.direccion.copy(codigoPostal = newPostalCode)
                    )
                )
            )
        }
    }
}


data class SucursalState(
    var currentSucursal: Sucursal = Sucursal(), var sucursalList: List<Sucursal> = emptyList()
)