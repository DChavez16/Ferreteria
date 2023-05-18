package controller.venta

import model.venta.VentaDatabase

class VentaController {
    var salesList = VentaDatabase.getVentaList()
}