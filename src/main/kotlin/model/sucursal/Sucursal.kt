package model.sucursal

import Database
import model.contacto.Contacto

data class Sucursal(
    // Primary key
    var id: Int? = null,

    // Atributes
    var name: String = "",

    // Foreign key
    val contacto: Contacto = Contacto()
)


object SucursalDatabase {
    private val statement = Database.connection.createStatement()

    /**
     * Collects all the branches stored in the database
     */
    fun getSucursalList(): List<Sucursal> {
        val newList = mutableListOf<Sucursal>()
        var currentSucursal: Sucursal

        val query = statement.executeQuery("select * from vista_Sucursal")

        while (query.next()) {
            currentSucursal = Sucursal()

            currentSucursal.id = query.getInt("idSucursal")
            currentSucursal.name = query.getString("nombre")
            currentSucursal.contacto.id = query.getInt("idContacto")
            currentSucursal.contacto.telefono = query.getString("telefono")
            currentSucursal.contacto.direccion.id = query.getInt("idDireccion")
            currentSucursal.contacto.direccion.municipio = query.getString("municipio")
            currentSucursal.contacto.direccion.colonia = query.getString("colonia")
            currentSucursal.contacto.direccion.calle = query.getString("calle")
            currentSucursal.contacto.direccion.numero = query.getInt("numero")
            currentSucursal.contacto.direccion.codigoPostal = query.getString("codigoPostal")

            newList.add(currentSucursal)
        }

        return newList.toList()
    }


    /**
     * Inserts the branch's data in the database
     * @param sucursal Sucursal to be added to the database
     */
    fun insertSucursal(sucursal: Sucursal): Boolean {
        val resultado = statement.executeUpdate(
            "execute insertSucursal '${sucursal.name}', '${sucursal.contacto.telefono}', '${sucursal.contacto.direccion.municipio}', '${sucursal.contacto.direccion.colonia}', '${sucursal.contacto.direccion.calle}', ${sucursal.contacto.direccion.numero}, '${sucursal.contacto.direccion.codigoPostal}'"
        )

        return resultado > 0
    }


    /**
     * Updates the indicated branch's entry in the database
     * @param sucursal Sucursal entry to be updated at the database
     */
    fun updateSucursal(sucursal: Sucursal): Boolean {
        val resultado = statement.executeUpdate(
            "execute updateSucursal ${sucursal.id}, '${sucursal.name}', ${sucursal.contacto.id}, '${sucursal.contacto.telefono}', ${sucursal.contacto.direccion.id}, '${sucursal.contacto.direccion.municipio}', '${sucursal.contacto.direccion.colonia}', '${sucursal.contacto.direccion.calle}', ${sucursal.contacto.direccion.numero}, '${sucursal.contacto.direccion.codigoPostal}'"
        )

        return resultado > 0
    }


    /**
     * Deletes the indicated brach's entry in the database
     * @param sucursal Sucursal to be deleted from the database
     */
    fun deleteSucursal(sucursal: Sucursal): Boolean {
        val resultado = statement.executeUpdate(
            "execute deleteSucursal ${sucursal.id}, ${sucursal.contacto.id}, ${sucursal.contacto.direccion.id}"
        )

        return resultado > 0
    }
}
