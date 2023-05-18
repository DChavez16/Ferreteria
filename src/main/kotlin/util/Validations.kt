package util

fun String.isValidPersonName() = Regex(pattern = "([a-z]|[A-Z]| |-)+").matches(this)

fun String.isValidEmail() = Regex(pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(this)

fun String.isValidPhoneNumber() = Regex(pattern = "(\\d){2} (\\d){4} (\\d){4}").matches(this)

fun String.isValidPostalCode() = Regex(pattern = "^(\\d){5}").matches(this)