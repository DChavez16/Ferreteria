package ui.mainContainer

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import util.NavigationOption
import util.NavigationOptions
import util.NavigationOptionsCodes
import util.UserType


@Composable
fun NavigationSideBar(
    navigationOptionsList: List<NavigationOption>,
    userType: UserType,
    closeProgram: () -> Unit,
    selectedItem: NavigationOptionsCodes,
    onNavigationOptionClicked: (NavigationOptionsCodes) -> Unit,
    onUserInfoClicked: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.secondary, elevation = 8.dp
    ) {
        Row {
            Column(
                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight().width(200.dp)
            ) {
                LazyColumn(contentPadding = PaddingValues(0.dp)) {
                    items(navigationOptionsList) { navigationOption ->
                        Column {
                            if (navigationOption.code == NavigationOptionsCodes.PRODUCTO) {
                                Divider(
                                    color = MaterialTheme.colors.secondaryVariant,
                                    thickness = Dp.Hairline,
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                )
                            }
                            NavigationOptionItem(
                                navigationOption = navigationOption,
                                isItemSelected = { selectedItem == it },
                                onNavigationOptionClicked = onNavigationOptionClicked
                            )
                        }
                    }
                }
                UserInfo(userType, closeProgram, onUserInfoClicked)
            }
            Divider(color = Color.Gray, modifier = Modifier.fillMaxHeight().width(Dp.Hairline))
        }
    }
}

@Composable
private fun NavigationOptionItem(
    navigationOption: NavigationOption,
    isItemSelected: (NavigationOptionsCodes) -> Boolean,
    onNavigationOptionClicked: (NavigationOptionsCodes) -> Unit
) {
    val isSelected = isItemSelected(navigationOption.code)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .height(
                if (isSelected) 60.dp
                else 50.dp
            )
            .background(
                if (isSelected) MaterialTheme.colors.secondaryVariant
                else MaterialTheme.colors.secondary
            )
            .clickable {
                onNavigationOptionClicked(navigationOption.code)
            }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessHigh
                )
            )
            .padding(vertical = 10.dp)
    )
    {
        Image(
            painter = painterResource(navigationOption.imagePath),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessHigh
                    )
                )
                .padding(
                    horizontal =
                    if (isSelected) 10.dp
                    else 15.dp
                )
        )
        Text(
            text = navigationOption.name, style = MaterialTheme.typography.h6
        )
    }
}

@Composable
private fun UserInfo(userType: UserType, closeProgram: () -> Unit, onUserInfoClicked: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Daniel Chavez", style = MaterialTheme.typography.body1, modifier = Modifier.clickable { onUserInfoClicked() })
        if (userType == UserType.ADMINISTRATOR) {
            Spacer(Modifier.height(4.dp))
            Text(text = "Administrador", style = MaterialTheme.typography.body1)
        }
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
        NavigationOptionItem(NavigationOptions(UserType.ADMINISTRATOR).navigationList[4], { false }) {}
    }
}

/*
Navigation sidebar preview
*/
@Preview
@Composable
fun NavigationSideBarPreview() {
    MaterialTheme {
        NavigationSideBar(
            navigationOptionsList = NavigationOptions(UserType.ADMINISTRATOR).navigationList,
            userType = UserType.CASHIER,
            closeProgram = {},
            selectedItem = NavigationOptionsCodes.INICIO,
            onNavigationOptionClicked = {},
            onUserInfoClicked = {}
        )
    }
}