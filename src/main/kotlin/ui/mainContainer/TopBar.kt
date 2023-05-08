package ui.mainContainer

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import controller.mainContainer.MainController
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds




@Composable
fun TopBar(mainController: MainController) {
    var dateTime by remember { mutableStateOf(mainController.getDateTime()) }
    val day by remember { mutableStateOf(mainController.getDay()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1.seconds)
            dateTime = mainController.getDateTime()
        }
    }

    Surface(
        color = MaterialTheme.colors.secondary, elevation = 16.dp
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(16.dp),
            ) {
                // Company logo and branch address
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource("images/logo.png"),
                        contentDescription = null,
                        modifier = Modifier.fillMaxHeight()
                    )
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text("Sucursal Anáhuac", style = MaterialTheme.typography.h6)
                        Spacer(Modifier.height(8.dp))
                        Text("San Nicolás, Nuevo León, 123", style = MaterialTheme.typography.h6)
                    }
                }
                // Current hour and date
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = dateTime, style = MaterialTheme.typography.h6)
                    Spacer(Modifier.height(8.dp))
                    Text(text = day, style = MaterialTheme.typography.h6)
                }
            }
            Divider(color = Color.Gray, thickness = Dp.Hairline)
        }
    }
}


/*
Top bar preview
*/
@Preview
@Composable
fun TopBarPreview() {
    MaterialTheme {
        TopBar(MainController())
    }
}