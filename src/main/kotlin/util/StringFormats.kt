package util

import model.fechaVenta.FechaVenta

fun getFechaString(fechaVenta: FechaVenta) = "${fechaVenta.dia}/${fechaVenta.mes}/${fechaVenta.anio}"