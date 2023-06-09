package model.direccion

data class Direccion(
    // Priomary key
    var id: Int? = null,

    // Atributes
    var municipio: String = "",
    var colonia: String = "",
    var calle: String = "",
    var numero: Int = 0,
    var codigoPostal: String = ""
)

// Direccion extension functions
fun Direccion.getFormat(): String = "${this.municipio}, ${this.colonia}, ${this.calle}, ${this.numero}, ${this.codigoPostal}"

val municipiosList = listOf(
    "Abasolo",
    "Agualeguas",
    "Los Aldamas",
    "Allende",
    "Anáhuac",
    "Apodaca",
    "Aramberri",
    "Bustamante",
    "Cadereyta Jiménez",
    "El Carmen",
    "Cerralvo",
    "Ciénega de Flores",
    "China",
    "Doctor Arroyo",
    "Doctor Coss",
    "Doctor González",
    "Galeana",
    "García",
    "San Pedro Garza García",
    "General Bravo",
    "General Escobedo",
    "General Terán",
    "General Treviño",
    "General Zaragoza",
    "General Zuazua",
    "Guadalupe",
    "Los Herreras",
    "Higueras",
    "Hualahuises",
    "Iturbide",
    "Juárez",
    "Lampazos de Naranjo",
    "Linares",
    "Marín",
    "Melchor Ocampo",
    "Mier y Noriega",
    "Mina",
    "Montemorelos",
    "Monterrey",
    "Parás",
    "Pesquería",
    "Los Ramones",
    "Rayones",
    "Sabinas Hidalgo",
    "Salinas Victoria",
    "San Nicolás de los Garza",
    "Hidalgo",
    "Santa Catarina",
    "Santiago",
    "Vallecillo",
    "Villaldama",
)