package model.cliente

import model.contacto.Contacto
import model.contacto.ContactoTestList

data class Cliente(
    var id: Long?,
    var nombre: String,
    var compras: Int,
    var suscrito: Boolean,
    var contacto: Contacto
)


val ClienteTestList = listOf(
    Cliente(1, "Cliente 1", 4, true, ContactoTestList[20]),
    Cliente(2, "Cliente 2", 8, false, ContactoTestList[21]),
    Cliente(3, "Cliente 3", 20, false, ContactoTestList[22]),
    Cliente(4, "Cliente 4", 15, false, ContactoTestList[23]),
    Cliente(5, "Cliente 5", 2, true, ContactoTestList[24]),
    Cliente(6, "Cliente 6", 2, false, ContactoTestList[25]),
    Cliente(7, "Cliente 7", 10, true, ContactoTestList[26]),
    Cliente(8, "Cliente 8", 16, false, ContactoTestList[27]),
    Cliente(9, "Cliente 9", 7, false, ContactoTestList[28]),
    Cliente(10, "Cliente 10", 8, false, ContactoTestList[29]),
    Cliente(11, "Cliente 11", 5, false, ContactoTestList[30]),
    Cliente(12, "Cliente 12", 7, true, ContactoTestList[31]),
    Cliente(13, "Cliente 13", 4, true, ContactoTestList[32]),
    Cliente(14, "Cliente 14", 8, false, ContactoTestList[33]),
    Cliente(15, "Cliente 15", 10, true, ContactoTestList[34]),
    Cliente(16, "Cliente 16", 15, false, ContactoTestList[35]),
    Cliente(17, "Cliente 17", 2, false, ContactoTestList[36]),
    Cliente(18, "Cliente 18", 5, false, ContactoTestList[37]),
    Cliente(19, "Cliente 19", 4, false, ContactoTestList[38]),
    Cliente(20, "Cliente 20", 0, true, ContactoTestList[39]),
    Cliente(21, "Cliente 21", 4, false, ContactoTestList[40]),
    Cliente(22, "Cliente 22", 8, false, ContactoTestList[41]),
    Cliente(23, "Cliente 23", 3, true, ContactoTestList[42]),
    Cliente(24, "Cliente 24", 5, false, ContactoTestList[43]),
    Cliente(25, "Cliente 25", 0, false, ContactoTestList[44]),
    Cliente(26, "Cliente 26", 1, false, ContactoTestList[45]),
    Cliente(27, "Cliente 27", 4, true, ContactoTestList[46]),
    Cliente(28, "Cliente 28", 5, false, ContactoTestList[47]),
    Cliente(29, "Cliente 29", 6, false, ContactoTestList[48]),
    Cliente(30, "Cliente 30", 3, false, ContactoTestList[49]),
    Cliente(31, "Cliente 31", 8, false, ContactoTestList[50]),
    Cliente(32, "Cliente 32", 2, false, ContactoTestList[51]),
    Cliente(33, "Cliente 33", 4, true, ContactoTestList[52]),
    Cliente(34, "Cliente 34", 0, false, ContactoTestList[53]),
    Cliente(35, "Cliente 35", 0, true, ContactoTestList[54]),
    Cliente(36, "Cliente 36", 2, false, ContactoTestList[55]),
    Cliente(37, "Cliente 37", 4, true, ContactoTestList[56]),
    Cliente(38, "Cliente 38", 2, false, ContactoTestList[57]),
    Cliente(39, "Cliente 39", 3, false, ContactoTestList[58]),
    Cliente(40, "Cliente 40", 6, true, ContactoTestList[59]),
)
