package model.cliente

import model.contacto.Contacto
import model.contacto.ContactoTestList
import model.detalleVentaProducto.DetalleVentaProducto

data class Cliente(
    var id: Long? = null,
    var nombre: String = "",
    var suscrito: Boolean = false,
    var contacto: Contacto = Contacto(),
    var cantidadCompras: Int = 0,
    var listaCompras: List<DetalleVentaProducto> = emptyList()
)


val ClienteTestList = listOf(
    Cliente(1, "Cliente 1", true, ContactoTestList[20]),
    Cliente(2, "Cliente 2", false, ContactoTestList[21]),
    Cliente(3, "Cliente 3", false, ContactoTestList[22]),
    Cliente(4, "Cliente 4", false, ContactoTestList[23]),
    Cliente(5, "Cliente 5", true, ContactoTestList[24]),
    Cliente(6, "Cliente 6", false, ContactoTestList[25]),
    Cliente(7, "Cliente 7", true, ContactoTestList[26]),
    Cliente(8, "Cliente 8", false, ContactoTestList[27]),
    Cliente(9, "Cliente 9", false, ContactoTestList[28]),
    Cliente(10, "Cliente 10", false, ContactoTestList[29]),
    Cliente(11, "Cliente 11", false, ContactoTestList[30]),
    Cliente(12, "Cliente 12", true, ContactoTestList[31]),
    Cliente(13, "Cliente 13", true, ContactoTestList[32]),
    Cliente(14, "Cliente 14", false, ContactoTestList[33]),
    Cliente(15, "Cliente 15", true, ContactoTestList[34]),
    Cliente(16, "Cliente 16", false, ContactoTestList[35]),
    Cliente(17, "Cliente 17", false, ContactoTestList[36]),
    Cliente(18, "Cliente 18", false, ContactoTestList[37]),
    Cliente(19, "Cliente 19", false, ContactoTestList[38]),
    Cliente(20, "Cliente 20", true, ContactoTestList[39]),
    Cliente(21, "Cliente 21", false, ContactoTestList[40]),
    Cliente(22, "Cliente 22", false, ContactoTestList[41]),
    Cliente(23, "Cliente 23", true, ContactoTestList[42]),
    Cliente(24, "Cliente 24", false, ContactoTestList[43]),
    Cliente(25, "Cliente 25", false, ContactoTestList[44]),
    Cliente(26, "Cliente 26", false, ContactoTestList[45]),
    Cliente(27, "Cliente 27", true, ContactoTestList[46]),
    Cliente(28, "Cliente 28", false, ContactoTestList[47]),
    Cliente(29, "Cliente 29", false, ContactoTestList[48]),
    Cliente(30, "Cliente 30", false, ContactoTestList[49]),
    Cliente(31, "Cliente 31", false, ContactoTestList[50]),
    Cliente(32, "Cliente 32", false, ContactoTestList[51]),
    Cliente(33, "Cliente 33", true, ContactoTestList[52]),
    Cliente(34, "Cliente 34", false, ContactoTestList[53]),
    Cliente(35, "Cliente 35", true, ContactoTestList[54]),
    Cliente(36, "Cliente 36", false, ContactoTestList[55]),
    Cliente(37, "Cliente 37", true, ContactoTestList[56]),
    Cliente(38, "Cliente 38", false, ContactoTestList[57]),
    Cliente(39, "Cliente 39", false, ContactoTestList[58]),
    Cliente(40, "Cliente 40", true, ContactoTestList[59])
)