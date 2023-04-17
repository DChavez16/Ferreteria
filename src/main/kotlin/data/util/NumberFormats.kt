package data.util

import java.text.DecimalFormat

val df = DecimalFormat("###,###,###.##")

fun decimalFormat(number: Double): String = df.format(number)