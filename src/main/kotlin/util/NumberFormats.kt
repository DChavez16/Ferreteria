package util

import java.text.DecimalFormat

val df = DecimalFormat("###,###,##0.00")

fun decimalFormat(number: Double): String = df.format(number)