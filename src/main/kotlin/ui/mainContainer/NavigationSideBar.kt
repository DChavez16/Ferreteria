package ui.mainContainer

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.util.NavigationOption
import data.util.NavigationOptions
import data.util.UserType


// TODO Add functionality
// TODO Modify to use SelectableGroup modifier

@Composable
fun NavigationSideBar(navigationOptionsList: List<NavigationOption>, closeProgram: () -> Unit) {
    Surface(
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight().width(200.dp)
        ) {
            LazyColumn(contentPadding = PaddingValues(0.dp)) {
                items(navigationOptionsList) { navigationOption ->
                    NavigationOptionItem(navigationOption)
                }
            }
            UserInfo(closeProgram)
        }
    }
}

@Composable
private fun NavigationOptionItem(navigationOption: NavigationOption) {
    var selected by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().height(50.dp).clickable { selected = !selected }
            .background(if (selected) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.secondary)
    ) {
        Image(
            painter = painterResource(navigationOption.imagePath),
            modifier = Modifier.fillMaxHeight().padding(10.dp).padding(horizontal = 10.dp),
            contentDescription = null
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = navigationOption.name,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
private fun UserInfo(closeProgram: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Daniel Chavez", style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(4.dp))
        Text(text = "Administrador", style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = closeProgram,
            elevation = ButtonDefaults.elevation(4.dp),
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text(text = "Salir", style = MaterialTheme.typography.button)
        }
    }
}


/*
Navigation item preview
*/
@Preview
@Composable
fun NavigationOptionItemPreview() {
    MaterialTheme {
        NavigationOptionItem(NavigationOptions(UserType.ADMINISTRATOR).navigationList[4])
    }
}

/*
Navigation sidebar preview
*/
@Preview
@Composable
fun NavigationSideBarPreview() {
    MaterialTheme {
        NavigationSideBar(NavigationOptions(UserType.ADMINISTRATOR).navigationList) {}
    }
}