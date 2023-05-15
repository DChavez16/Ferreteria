package controller.cliente

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.cliente.Cliente
import model.cliente.ClienteDatabase
import model.detalleVentaProducto.DetalleVentaProducto

class ClienteController {
    private var _clienteState = MutableStateFlow(ClienteState())
    val clienteState: StateFlow<ClienteState> = _clienteState.asStateFlow()

    init {
        getClienteList()
    }

    /*
    *  Methods
    */
    /**
     * Retrieves a list of clients from the database
     */
    private fun getClienteList() {
        _clienteState.value.clienteList = ClienteDatabase.getClienteList()
    }

    /**
     * Retrieves a list of the client's purchases from the database
     */
    private fun getComprasCliente(id: Int?) {
        var newList: List<DetalleVentaProducto> = emptyList()

        id?.let {
            newList = ClienteDatabase.getComprasCliente(id)
        }

        _clienteState.update { currentState ->
            currentState.copy(
                currentCliente = currentState.currentCliente.copy(
                    listaCompras = newList
                )
            )
        }
    }

    /**
     * Updates the current client
     * @param cliente The current client selected to edit, can be empty
     */
    fun changeCurrentCliente(cliente: Cliente = Cliente()) {
        _clienteState.update { currentState ->
            currentState.copy(currentCliente = cliente)
        }

        getComprasCliente(_clienteState.value.currentCliente.id)
    }

    /**
     * Adds a new client to the database
     * @param cliente Client to be deleted from the database
     */
    fun createCliente(cliente: Cliente) {
        if(ClienteDatabase.insertCliente(cliente)) getClienteList()
    }

    /**
     * Edits the current client from the database
     * @param cliente Client to be edited in the database
     */
    fun updateCliente(cliente: Cliente) {
        if(ClienteDatabase.updateCliente(cliente)) getClienteList()
    }

    /**
     * Deletes the current client
     * @param cliente Client to be deleted from the database
     */
    fun deleteCliente(cliente: Cliente) {
        if(ClienteDatabase.deleteCliente(cliente)) getClienteList()
    }

    /**
     * Validates if the current client's content is not empty
     */
    fun clienteIsNotEmpty() = with(_clienteState.value.currentCliente) {
        this.nombre.isNotEmpty() && with(this.contacto) {
            this.correo.isNotEmpty() && this.telefono.isNotEmpty()
        }
    }

    /**
     * Clears the current client's content
     */
    fun clearCliente() {
        _clienteState.update { currentState ->
            currentState.copy(
                currentCliente = currentState.currentCliente.copy(
                    nombre = "",
                    suscrito = false,
                    contacto = currentState.currentCliente.contacto.copy(
                        correo = "",
                        telefono = ""
                    )
                )
            )
        }
    }

    /**
     * Updates the name of the client
     * @param newName New name of the client
     */
    fun updateClientName(newName: String) {
        _clienteState.update { currentState ->
            currentState.copy(currentCliente = currentState.currentCliente.copy(nombre = newName))
        }
    }

    /**
     * Updates the subscription of the client
     * @param newSubscription New name of the client
     */
    fun updateClientSuscription(newSubscription: Boolean) {
        _clienteState.update { currentState ->
            currentState.copy(currentCliente = currentState.currentCliente.copy(suscrito = newSubscription))
        }
    }

    /**
     * Updates the email of the client
     * @param newEmail New email of the client
     */
    fun updateClientEmail(newEmail: String) {
        _clienteState.update { currentState ->
            currentState.copy(currentCliente = currentState.currentCliente.copy(
                contacto = currentState.currentCliente.contacto.copy(correo = newEmail)
            ))
        }
    }

    /**
     * Updates the phone of the client
     * @param newPhone New phone of the client
     */
    fun updateClientPhone(newPhone: String) {
        _clienteState.update { currentState ->
            currentState.copy(currentCliente = currentState.currentCliente.copy(
                contacto = currentState.currentCliente.contacto.copy(telefono = newPhone)
            ))
        }
    }
}


data class ClienteState(
    var currentCliente: Cliente = Cliente(),
    var clienteList: List<Cliente> = emptyList()
)