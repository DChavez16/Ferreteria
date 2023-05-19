package controller.mainContainer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import util.NavigationOption
import util.NavigationOptions
import util.UserType
import java.util.*

private lateinit var cal: Calendar

class MainController {
    // StateFlow of the user type
    private var _userType = MutableStateFlow(UserType.ADMINISTRATOR)
    var userType: StateFlow<UserType> = _userType.asStateFlow()

    // List of options showing depending on the user type
    var optionsList: List<NavigationOption>

    init {
        optionsList = NavigationOptions(_userType.value).navigationList
    }

    /*
    Helper methods
    */
    fun getDateTime(): String {
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

    fun getDay(): String {
        cal = Calendar.getInstance()

        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH) + 1
        val year = cal.get(Calendar.YEAR)

        return "${day}/${month}/${year}"
    }

    // Changes the type of the user and updates the list of options
    fun changeUserType(tipo: UserType) {
        _userType.value = tipo
        optionsList = NavigationOptions(_userType.value).navigationList
    }
}