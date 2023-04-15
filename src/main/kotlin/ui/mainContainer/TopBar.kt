package ui.mainContainer

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


// TODO Add functionality

@Composable
fun TopBar() {
    Surface(
        color = MaterialTheme.colors.secondaryVariant,
        elevation = 16.dp
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
                    Column() {
                        Text("Sucursal Anahuac", style = MaterialTheme.typography.body1)
                        Spacer(Modifier.height(8.dp))
                        Text("San Nicolas, Nuevo Leon, 123", style = MaterialTheme.typography.body1)
                    }
                }
                // Current hour and date
                Column {
                    Text(text = "Hora: 13:15", style = MaterialTheme.typography.body1)
                    Spacer(Modifier.height(8.dp))
                    Text(text = "13/04/23", style = MaterialTheme.typography.body1)
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
        TopBar()
    }
}