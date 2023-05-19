package ui.mainContainer

import ProgramEscencials
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import controller.mainContainer.MainController
import util.NavigationOption
import util.NavigationOptions
import util.NavigationOptionsCodes
import util.UserType

@Composable
fun NavigationSideBar(
    mainController: MainController,
    closeProgram: () -> Unit,
    selectedItem: NavigationOptionsCodes,
    onNavigationOptionClicked: (NavigationOptionsCodes) -> Unit
) {
    val empleado = ProgramEscencials.selectedEmpleado
    mainController.changeUserType(empleado.puesto)

    Surface(
        color = MaterialTheme.colors.secondaryVariant, elevation = 8.dp
    ) {
        Row {
            Column(
                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight().width(200.dp)
            ) {
                LazyColumn(contentPadding = PaddingValues(0.dp)) {
                    items(mainController.optionsList) { navigationOption ->
                        if (navigationOption.code == NavigationOptionsCodes.PRODUCTO) {
                            Divider(
                                color = MaterialTheme.colors.secondary,
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
                UserInfo(nombre = "${empleado.primerNombre} ${empleado.apellido}",
                    userType = mainController.userType.value,
                    closeProgram = closeProgram,
                    onLogoutClick = { ProgramEscencials.changeWindow() })
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
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().height(
            if (isSelected) 60.dp
            else 50.dp
        ).background(
            if (isSelected) MaterialTheme.colors.secondary
            else MaterialTheme.colors.secondaryVariant
        ).clickable {
            onNavigationOptionClicked(navigationOption.code)
        }.animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessHigh
            )
        ).padding(vertical = 10.dp)
    ) {
        Image(
            painter = painterResource(navigationOption.imagePath),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessHigh
                )
            ).padding(
                horizontal = if (isSelected) 10.dp
                else 15.dp
            )
        )
        Text(
            text = navigationOption.name, style = MaterialTheme.typography.h6
        )
    }
}

@Composable
private fun UserInfo(nombre: String, userType: UserType, closeProgram: () -> Unit, onLogoutClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = nombre,
            style = MaterialTheme.typography.body1,
        )
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
        Spacer(Modifier.height(4.dp))
        Text(text = "Cerrar sesión",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().clickable { onLogoutClick() })
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
        NavigationSideBar(mainController = MainController(),
            closeProgram = {},
            selectedItem = NavigationOptionsCodes.INICIO,
            onNavigationOptionClicked = {})
    }
}