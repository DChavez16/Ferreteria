package controller.login

import ProgramEscencials
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.empleado.Empleado
import model.empleado.EmpleadoDatabase
import model.sucursal.Sucursal
import model.sucursal.SucursalDatabase
import util.isValidEmail
import util.isValidPhoneNumber

object LoginController {
    private var _correo = MutableStateFlow("")
    var correo: StateFlow<String> = _correo.asStateFlow()

    private var _telefono = MutableStateFlow("")
    var telefono: StateFlow<String> = _telefono.asStateFlow()

    private var _error = MutableStateFlow(false)
    var error: StateFlow<Boolean> = _error.asStateFlow()

    private var empleadoList: List<Empleado> = emptyList()

    init {
        ProgramEscencials.reset()
        getEmpleadosList()
        resetState()
    }

    private fun resetState() {
        _correo.value = ""
        _telefono.value = ""
        _error.value = false
    }

    private fun getEmpleadosList() {
        empleadoList = EmpleadoDatabase.getEmpleadoList()
    }

    fun updateEmpleadoEmail(newEmail: String) {
        _correo.value = newEmail
    }

    fun updateEmpleadoPhone(newPhone: String) {
        _telefono.value = newPhone
    }

    fun isLoginButtonEnabled(): Boolean = _correo.value.isValidEmail() && _telefono.value.isValidPhoneNumber()

    fun onLoginButtonClick() {
        val selectedEmpleado: Empleado?
        val selectedSucursal: Sucursal?

        try {
            selectedEmpleado =
                empleadoList.filter { it.contacto.correo == _correo.value && it.contacto.telefono == _telefono.value }[0]

            ProgramEscencials.updateEmpleado(selectedEmpleado)

            selectedSucursal = SucursalDatabase.getSucursal(selectedEmpleado.sucursal.id!!)

            if (selectedSucursal != null) {
                ProgramEscencials.updateSucursal(selectedSucursal)
                ProgramEscencials.changeWindow()
            }
        } catch (_: IndexOutOfBoundsException) {
            _error.value = true
        }
    }
}