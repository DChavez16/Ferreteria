package util

fun Boolean.toByte(): Byte {
    return when(this) {
        false -> 0.toByte()
        else -> 1.toByte()
    }
}