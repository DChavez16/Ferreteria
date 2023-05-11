package controller.reporte

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.reporte.Reporte
import model.reporte.ReporteTestList

class ReporteController {
    private var _reporteState = MutableStateFlow(ReporteState())
    val reporteState: StateFlow<ReporteState> = _reporteState.asStateFlow()

    init {
        getProveedorList()
    }

    private fun getProveedorList() {
        _reporteState.value.reporteList = ReporteTestList
    }
}

data class ReporteState(
    var reporteList: List<Reporte> = emptyList()
)