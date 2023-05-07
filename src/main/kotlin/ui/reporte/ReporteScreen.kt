package ui.reporte

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import model.reporte.ReporteTestList

@Composable
fun ReporteScreen() {
    Surface(color = MaterialTheme.colors.background) {
        ReporteList(ReporteTestList)
    }
}