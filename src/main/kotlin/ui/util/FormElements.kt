package ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableDropDownMenu(
    value: String, optionsList: List<String>, onValueChange: (Any) -> Unit, modifier: Modifier = Modifier
) {
    var expandedDropDownMenu by remember { mutableStateOf(false) }
    val icon = painterResource("icons/${if (expandedDropDownMenu) "expand_less" else "expand_more"}.png")

    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth().height(55.dp).background(Color.LightGray)
            .clickable { expandedDropDownMenu = !expandedDropDownMenu }) {
            // Value text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight().weight(1f)
            ) {
                Text(
                    text = value, style = MaterialTheme.typography.h6, modifier = Modifier.padding(8.dp)
                )
            }

            // Expand dropdown menu button
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.size(55.dp)
            ) {
                Icon(
                    painter = icon, contentDescription = null
                )
            }
        }

        // Dropdow menu showing all available options
        DropdownMenu(
            expanded = expandedDropDownMenu,
            onDismissRequest = { expandedDropDownMenu = false },
            modifier = Modifier.requiredHeightIn(max = 250.dp).background(Color.LightGray)
        ) {
            optionsList.forEach { option ->
                DropdownMenuItem(onClick = {
                    onValueChange(option)
                    expandedDropDownMenu = false
                }) {
                    Text(text = option, color = Color.Black, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}