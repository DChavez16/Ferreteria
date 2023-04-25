package util

import data.model.FechaVenta

fun getFechaString(fechaVenta: FechaVenta) = "${fechaVenta.dia}/${fechaVenta.mes}/${fechaVenta.anio}"