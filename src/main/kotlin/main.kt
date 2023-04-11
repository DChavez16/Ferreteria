import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = { exitApplication() },
        title = "Titulo de la aplicacion",
        state = rememberWindowState(width = 300.dp, height = 300.dp)
    ) {
        MaterialTheme {
            MainScreen()
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    // Variable del contador
    val count = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Boton para incrementar el contador
        Button(content = {
            Text(if (count.value == 0) "Hola mundo" else "Click numero: ${count.value}")
        }, onClick = {
            count.value++
        })

        // Boton para reiniciar el valor del contador
        Button(content = {
            Text("Reiniciar el conteo")
        }, onClick = {
            count.value = 0
        }, enabled = count.value != 0
        )
    }
}