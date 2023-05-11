package ui.reporte

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import controller.reporte.ReporteController

@Composable
fun ReporteScreen() {
    val ReporteController = ReporteController()

    val reporteState = ReporteController.reporteState.collectAsState()

    Surface(color = MaterialTheme.colors.background) {
        ReporteList(reporteState.value.reporteList)
    }
}