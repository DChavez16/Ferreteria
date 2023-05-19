package ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import controller.login.LoginController
import util.getCustomOutlinedTextFieldColor
import util.isValidEmail
import util.isValidPhoneNumber

@Composable
fun LoginWindow() {
    val correo = LoginController.correo.collectAsState()
    val telefono = LoginController.telefono.collectAsState()
    val error = LoginController.error.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        // Program logo
        Image(
            painter = painterResource("images/logo.png"), contentDescription = null, modifier = Modifier.weight(1f)
        )

        // Text fields and login button
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            // Correo and Telefono text fields
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                // Correo text field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(0.45f)
                ) {
                    Text(text = "Correo:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                    OutlinedTextField(value = correo.value,
                        onValueChange = { LoginController.updateEmpleadoEmail(it) },
                        placeholder = { Text("example@email.com") },
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        isError = !correo.value.isValidEmail(),
                        singleLine = true,
                        modifier = Modifier.weight(2f)
                    )
                }
                Spacer(Modifier.weight(0.1f))

                // Telefono text field
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(0.45f)
                ) {
                    Text(text = "Teléfono:", style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                    OutlinedTextField(value = telefono.value,
                        onValueChange = { LoginController.updateEmpleadoPhone(it) },
                        placeholder = { Text("00 0000 0000") },
                        textStyle = MaterialTheme.typography.h6,
                        colors = getCustomOutlinedTextFieldColor(),
                        isError = !telefono.value.isValidPhoneNumber(),
                        singleLine = true,
                        modifier = Modifier.weight(2f)
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Login button
                Button(
                    onClick = { LoginController.onLoginButtonClick() },
                    elevation = ButtonDefaults.elevation(4.dp),
                    enabled = LoginController.isLoginButtonEnabled()
                ) {
                    Text(text = "Entrar", style = MaterialTheme.typography.button)
                }
                if(error.value) {
                    Text(
                        text = "No existe un usuario con las credenciales solicitadas",
                        style = MaterialTheme.typography.body2,
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}