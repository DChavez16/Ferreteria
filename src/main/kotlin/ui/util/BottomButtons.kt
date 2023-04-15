package ui.util

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomButtons(
    twoButtons: Boolean,
    firstButtonText: String,
    firstButtonAction: Unit,
    secondButtonText: String = "",
    secondButtonAction: Unit = Unit,
    firstButtonEnabled: Boolean = true,
    secondButtonEnabled: Boolean = true
) {
    Row(
        horizontalArrangement = if (twoButtons) Arrangement.End else Arrangement.Start,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        if (twoButtons) {
            OutlinedButton(onClick = { secondButtonAction }, enabled = secondButtonEnabled) {
                Text(text = secondButtonText, style = MaterialTheme.typography.button)
            }
            Spacer(Modifier.width(10.dp))
        }
        Button(onClick = { firstButtonAction }, enabled = firstButtonEnabled) {
            Text(text = firstButtonText, style = MaterialTheme.typography.button)
        }
    }
}


// BottomActionButtons preview composable
@Preview
@Composable
private fun BottomButtonsOneButtonPreview() {
    MaterialTheme {
        BottomButtons(false, "Agregar", Unit)
    }
}

@Preview
@Composable
private fun BottomButtonsPreview() {
    MaterialTheme {
        BottomButtons(true, "Limpiar campos", Unit, "Aceptar", Unit, secondButtonEnabled = false)
    }
}