package util

import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun getCustomOutlinedTextFieldColor() = TextFieldDefaults.outlinedTextFieldColors(
    focusedBorderColor = MaterialTheme.colors.primary,
    unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
    focusedLabelColor = MaterialTheme.colors.primary,
    unfocusedLabelColor = MaterialTheme.colors.primaryVariant,
    textColor = Color.Black,
    placeholderColor = Color.Gray
)

@Composable
fun getCustomCheckboxColor() = CheckboxDefaults.colors(
    checkedColor = MaterialTheme.colors.secondary,
    uncheckedColor = MaterialTheme.colors.secondaryVariant,
    checkmarkColor = MaterialTheme.colors.primary
)

@Composable
fun getCustomRadioButtonColor() = RadioButtonDefaults.colors(
    selectedColor = MaterialTheme.colors.secondary,
    unselectedColor = MaterialTheme.colors.secondaryVariant
)