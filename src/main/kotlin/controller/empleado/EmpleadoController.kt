package controller.empleado

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.detalleVentaProducto.DetalleVentaProducto
import model.empleado.Empleado
import model.empleado.EmpleadoDatabase
import model.sucursal.Sucursal
import model.sucursal.SucursalDatabase
import util.UserType

class EmpleadoController {
    private var _empleadoState = MutableStateFlow(EmpleadoState())
    val empleadoState: StateFlow<EmpleadoState> = _empleadoState.asStateFlow()

    val sucursalNamePair: MutableList<Pair<String, Sucursal>> = mutableListOf()

    init {
        getEmpleadoList()
        getSucursalList()
    }

    /*
    *  Methods
    */
    /**
     * Retrieves a list of employees from the database
     */
    private fun getEmpleadoList() {
        _empleadoState.value.empleadoList = EmpleadoDatabase.getEmpleadoList()
    }

    /**
     * Retrieves a list of employee's sells from the database
     * @paramid ID of the Empleado which sells are going to be retrieved
     */
    private fun getVentasEmpleado(id: Int?) {
        var newList: List<DetalleVentaProducto> = emptyList()

        id?.let {
            newList = EmpleadoDatabase.getVentasEmpleado(id)
        }

        _empleadoState.update { currentState ->
            currentState.copy(
                currentEmpleado = currentState.currentEmpleado.copy(
                    listaVentas = newList
                )
            )
        }
    }

    /**
     * Retrieves a list of branches from the database
     */
    private fun getSucursalList() {
        _empleadoState.value.sucursalList = SucursalDatabase.getSucursalList()

        _empleadoState.value.sucursalList.forEach { sucursal ->
            sucursalNamePair.add(Pair(sucursal.name, sucursal))
        }
    }

    /**
     * Updates the current employee
     * @param empleado The current employee selected to edit, can be empty
     */
    fun changeCurrentEmpleado(empleado: Empleado = Empleado()) {
        _empleadoState.update { currentState ->
            currentState.copy(currentEmpleado = empleado)
        }

        getVentasEmpleado(_empleadoState.value.currentEmpleado.id)
    }

    /**
     * Adds a new employee to the database
     * @param empleado Employee to be deleted from the database
     */
    fun createEmpleado(empleado: Empleado) {
        if(EmpleadoDatabase.insertEmpleado(empleado)) getEmpleadoList()
    }

    /**
     * Edits the current employee from the database
     * @param empleado Empleado to be edited in the database
     */
    fun updateEmpleado(empleado: Empleado) {
        if(EmpleadoDatabase.updateEmpleado(empleado)) getEmpleadoList()
    }

    /**
     * Deletes the current employee
     * @param empleado Employee to be deleted from the database
     */
    fun deleteEmpleado(empleado: Empleado) {
        if(EmpleadoDatabase.deleteEmpleado(empleado)) getEmpleadoList()
    }

    /**
     * Validates if the current employee's content is not empty
     */
    fun empleadoIsNotEmpty() = with(_empleadoState.value.currentEmpleado) {
        this.nombre.isNotEmpty() && this.sucursal.id != null && with(this.contacto) {
            this.correo.isNotEmpty() && this.telefono.isNotEmpty()
        }
    }

    /**
     * Clears the current employee's content
     */
    fun clearEmpleado() {
        _empleadoState.update { currentState ->
            currentState.copy(
                currentEmpleado = currentState.currentEmpleado.copy(
                    nombre = "",
                    puesto = UserType.CASHIER,
                    sucursal = Sucursal(),
                    contacto = currentState.currentEmpleado.contacto.copy(
                        correo = "", telefono = ""
                    )
                )
            )
        }
    }

    /**
     * Updates the name of the employee
     * @param newName New name of the employee
     */
    fun updateEmployeeName(newName: String) {
        _empleadoState.update { currentState ->
            currentState.copy(currentEmpleado = currentState.currentEmpleado.copy(nombre = newName))
        }
    }

    /**
     * Updates the position of the employee
     * @param newPosition Boolean value, if true, the employee's position is defined as administrator
     */
    fun updateEmployeePosition(newPosition: Boolean) {
        _empleadoState.update { currentState ->
            currentState.copy(
                currentEmpleado = currentState.currentEmpleado.copy(
                    puesto = if (newPosition) UserType.ADMINISTRATOR else UserType.CASHIER
                )
            )
        }
    }

    /**
     * Updates the phone of the employee
     * @param newPhone New phone of the employee
     */
    fun updateEmployeePhone(newPhone: String) {
        _empleadoState.update { currentState ->
            currentState.copy(
                currentEmpleado = currentState.currentEmpleado.copy(
                    contacto = currentState.currentEmpleado.contacto.copy(telefono = newPhone)
                )
            )
        }
    }

    /**
     * Updates the email of the employee
     * @param newEmail New email of the employee
     */
    fun updateEmployeeEmail(newEmail: String) {
        _empleadoState.update { currentState ->
            currentState.copy(
                currentEmpleado = currentState.currentEmpleado.copy(
                    contacto = currentState.currentEmpleado.contacto.copy(correo = newEmail)
                )
            )
        }
    }

    /**
     * Updates the branch of the employee
     * @param newBranchString New branch of the employee
     */
    fun updateEmployeeBranch(newBranchString: String) {
        val sucursal = sucursalNamePair.find { it.first == newBranchString }?.second ?: Sucursal()

        // TODO Complete code
        _empleadoState.update { currentState ->
            currentState.copy(currentEmpleado = currentState.currentEmpleado.copy(sucursal = sucursal))
        }
    }
}


data class EmpleadoState(
    var currentEmpleado: Empleado = Empleado(),
    var empleadoList: List<Empleado> = emptyList(),
    var sucursalList: List<Sucursal> = emptyList()
)