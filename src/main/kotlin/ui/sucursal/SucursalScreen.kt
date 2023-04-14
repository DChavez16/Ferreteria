package ui.sucursal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun SucursalScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Icon(painter = painterResource("icons/sucursal.png"), contentDescription = null, modifier = Modifier.fillMaxSize())
    }

    // Sucursal list screen
    SucursalList()

    // Add sucursal screen
    AddSucursalScreen()

    // Edit sucursal screen
    EditSucursalScreen()
}