package ui.util

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenHeader(headerTitle: String, onReturnButtonClick: () -> Unit) {
    Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onReturnButtonClick() }.padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            Spacer(Modifier.width(4.dp))
            Text(text = "Regresar", style = MaterialTheme.typography.h6)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = headerTitle,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview
@Composable
fun a() {
    MaterialTheme {
        ScreenHeader("Titulo") {}
    }
}