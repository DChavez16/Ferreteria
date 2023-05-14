package ui.util

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenHeader(headerTitle: String, onReturnButtonClick: () -> Unit) {
    Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        var returnButtonHover by remember { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onReturnButtonClick() }
                .onPointerEvent(PointerEventType.Enter) { returnButtonHover = true }
                .onPointerEvent(PointerEventType.Exit) { returnButtonHover = false }
                .padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Regresar",
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                style = MaterialTheme.typography.h6,
                textDecoration = with(TextDecoration) { if (returnButtonHover) Underline else None }
            )
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