package controller.reporte

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.reporte.Reporte
import model.reporte.ReporteDatabase

class ReporteController {
    private var _reporteState = MutableStateFlow(ReporteState())
    val reporteState: StateFlow<ReporteState> = _reporteState.asStateFlow()

    init {
        getReporteList()
    }

    private fun getReporteList() {
        _reporteState.value.reporteList = ReporteDatabase.getReporteList()
    }
}

data class ReporteState(
    var reporteList: List<Reporte> = emptyList()
)