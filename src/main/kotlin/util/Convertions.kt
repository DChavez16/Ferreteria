package util

/**
 * Converts a boolean value to a byte type
 */
fun Boolean.toByte(): Byte {
    return when(this) {
        false -> 0.toByte()
        else -> 1.toByte()
    }
}

/**
 * Converts a UserType value to a byte type
 */
fun UserType.toByte(): Byte {
    return when(this) {
        UserType.CASHIER -> 0.toByte()
        else -> 1.toByte()
    }
}

/**
 * Converts a Byte value to a UserType type
 */
fun Byte.toUserType(): UserType {
    return when(this) {
        0.toByte() -> UserType.CASHIER
        else -> UserType.ADMINISTRATOR
    }
}