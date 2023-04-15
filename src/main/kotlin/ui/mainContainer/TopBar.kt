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
import kotlinx.coroutines.delay
import java.util.*
import kotlin.time.Duration.Companion.seconds


private lateinit var cal: Calendar

@Composable
fun TopBar() {
    var dateTime by remember { mutableStateOf(getDateTime()) }
    var day by remember { mutableStateOf(getDay()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1.seconds)
            dateTime = getDateTime()
        }
    }

    Surface(
        color = MaterialTheme.colors.secondaryVariant, elevation = 16.dp
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
                        Text("Sucursal Anahuac", style = MaterialTheme.typography.body1)
                        Spacer(Modifier.height(8.dp))
                        Text("San Nicolas, Nuevo Leon, 123", style = MaterialTheme.typography.body1)
                    }
                }
                // Current hour and date
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = dateTime, style = MaterialTheme.typography.body1)
                    Spacer(Modifier.height(8.dp))
                    Text(text = day, style = MaterialTheme.typography.body1)
                }
            }
            Divider(color = Color.Gray, thickness = Dp.Hairline)
        }
    }
}


/*
Helper methods
*/
private fun getDateTime(): String {
    cal = Calendar.getInstance()

    val hour = cal.get(Calendar.HOUR_OF_DAY)
    val min = with(cal.get(Calendar.MINUTE)) {
        if (this < 10) "0$this" else this
    }
    val sec = with(cal.get(Calendar.SECOND)) {
        if (this < 10) "0$this" else this
    }

    return "${hour}:${min}:${sec}"
}

private fun getDay(): String {
    cal = Calendar.getInstance()

    val day = cal.get(Calendar.DAY_OF_MONTH)
    val month = cal.get(Calendar.MONTH) + 1
    val year = cal.get(Calendar.YEAR)

    return "${day}/${month}/${year}"
}


/*
Top bar preview
*/
@Preview
@Composable
fun TopBarPreview() {
    MaterialTheme {
        TopBar()
    }
}