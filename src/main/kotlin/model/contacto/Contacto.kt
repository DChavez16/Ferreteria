package model.contacto

import model.direccion.Direccion
import model.direccion.DireccionTestList

data class Contacto(
    // Primary key
    var id: Long? = null,

    // Atributes
    var correo: String = "",
    var telefono: String = "",

    // Foreign key
    var direccion: Direccion = Direccion()
)

val ContactoTestList = listOf(
    Contacto(1, "correo1@gmail.com", "00 0000 0001", DireccionTestList[0]),
    Contacto(2, "correo2@gmail.com", "00 0000 0002", DireccionTestList[1]),
    Contacto(3, "correo3@gmail.com", "00 0000 0003", DireccionTestList[2]),
    Contacto(4, "correo4@gmail.com", "00 0000 0004", DireccionTestList[3]),
    Contacto(5, "correo5@gmail.com", "00 0000 0005", DireccionTestList[4]),
    Contacto(6, "correo6@gmail.com", "00 0000 0006", DireccionTestList[5]),
    Contacto(7, "correo7@gmail.com", "00 0000 0007", DireccionTestList[6]),
    Contacto(8, "correo8@gmail.com", "00 0000 0008", DireccionTestList[7]),
    Contacto(9, "correo9@gmail.com", "00 0000 0009", DireccionTestList[8]),
    Contacto(10, "correo10@gmail.com", "00 0000 0010", DireccionTestList[9]),
    Contacto(11, "correo11@gmail.com", "00 0000 0011", DireccionTestList[10]),
    Contacto(12, "correo12@gmail.com", "00 0000 0012", DireccionTestList[11]),
    Contacto(13, "correo13@gmail.com", "00 0000 0013", DireccionTestList[12]),
    Contacto(14, "correo14@gmail.com", "00 0000 0014", DireccionTestList[13]),
    Contacto(15, "correo15@gmail.com", "00 0000 0015", DireccionTestList[14]),
    Contacto(16, "correo16@gmail.com", "00 0000 0016", DireccionTestList[15]),
    Contacto(17, "correo17@gmail.com", "00 0000 0017", DireccionTestList[16]),
    Contacto(18, "correo18@gmail.com", "00 0000 0018", DireccionTestList[17]),
    Contacto(19, "correo19@gmail.com", "00 0000 0019", DireccionTestList[18]),
    Contacto(20, "correo20@gmail.com", "00 0000 0020", DireccionTestList[19]),
    Contacto(21, "correo21@gmail.com", "00 0000 0021", DireccionTestList[20]),
    Contacto(22, "correo22@gmail.com", "00 0000 0022", DireccionTestList[21]),
    Contacto(23, "correo23@gmail.com", "00 0000 0023", DireccionTestList[22]),
    Contacto(24, "correo24@gmail.com", "00 0000 0024", DireccionTestList[23]),
    Contacto(25, "correo25@gmail.com", "00 0000 0025", DireccionTestList[24]),
    Contacto(26, "correo26@gmail.com", "00 0000 0026", DireccionTestList[25]),
    Contacto(27, "correo27@gmail.com", "00 0000 0027", DireccionTestList[26]),
    Contacto(28, "correo28@gmail.com", "00 0000 0028", DireccionTestList[27]),
    Contacto(29, "correo29@gmail.com", "00 0000 0029", DireccionTestList[28]),
    Contacto(30, "correo30@gmail.com", "00 0000 0030", DireccionTestList[29]),
    Contacto(31, "correo31@gmail.com", "00 0000 0031", DireccionTestList[30]),
    Contacto(32, "correo32@gmail.com", "00 0000 0032", DireccionTestList[31]),
    Contacto(33, "correo33@gmail.com", "00 0000 0033", DireccionTestList[32]),
    Contacto(34, "correo34@gmail.com", "00 0000 0034", DireccionTestList[33]),
    Contacto(35, "correo35@gmail.com", "00 0000 0035", DireccionTestList[34]),
    Contacto(36, "correo36@gmail.com", "00 0000 0036", DireccionTestList[35]),
    Contacto(37, "correo37@gmail.com", "00 0000 0037", DireccionTestList[36]),
    Contacto(38, "correo38@gmail.com", "00 0000 0038", DireccionTestList[37]),
    Contacto(39, "correo39@gmail.com", "00 0000 0039", DireccionTestList[38]),
    Contacto(40, "correo40@gmail.com", "00 0000 0040", DireccionTestList[39]),
    Contacto(41, "correo41@gmail.com", "00 0000 0041", DireccionTestList[40]),
    Contacto(42, "correo42@gmail.com", "00 0000 0042", DireccionTestList[41]),
    Contacto(43, "correo43@gmail.com", "00 0000 0043", DireccionTestList[42]),
    Contacto(44, "correo44@gmail.com", "00 0000 0044", DireccionTestList[43]),
    Contacto(45, "correo45@gmail.com", "00 0000 0045", DireccionTestList[44]),
    Contacto(46, "correo46@gmail.com", "00 0000 0046", DireccionTestList[45]),
    Contacto(47, "correo47@gmail.com", "00 0000 0047", DireccionTestList[46]),
    Contacto(48, "correo48@gmail.com", "00 0000 0048", DireccionTestList[47]),
    Contacto(49, "correo49@gmail.com", "00 0000 0049", DireccionTestList[48]),
    Contacto(50, "correo50@gmail.com", "00 0000 0050", DireccionTestList[49]),
    Contacto(51, "correo51@gmail.com", "00 0000 0051", DireccionTestList[50]),
    Contacto(52, "correo52@gmail.com", "00 0000 0052", DireccionTestList[51]),
    Contacto(53, "correo53@gmail.com", "00 0000 0053", DireccionTestList[52]),
    Contacto(54, "correo54@gmail.com", "00 0000 0054", DireccionTestList[53]),
    Contacto(55, "correo55@gmail.com", "00 0000 0055", DireccionTestList[54]),
    Contacto(56, "correo56@gmail.com", "00 0000 0056", DireccionTestList[55]),
    Contacto(57, "correo57@gmail.com", "00 0000 0057", DireccionTestList[56]),
    Contacto(58, "correo58@gmail.com", "00 0000 0058", DireccionTestList[57]),
    Contacto(59, "correo59@gmail.com", "00 0000 0059", DireccionTestList[58]),
    Contacto(60, "correo60@gmail.com", "00 0000 0060", DireccionTestList[59])
)