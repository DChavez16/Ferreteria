package model.proveedor

import Database
import model.contacto.Contacto
import model.producto.Producto

data class Proveedor(
    var id: Int? = null,
    var nombre: String = "",
    var contacto: Contacto = Contacto(),
    var productos: List<Producto> = emptyList()
)



object ProveedorDatabase {
    private val statement = Database.connection.createStatement()

    /**
     * Collects all the suppliers stored in the database
     */
    fun getProveedorList(): List<Proveedor> {
        val newList = mutableListOf<Proveedor>()
        var currentProveedor: Proveedor

        val query = statement.executeQuery("select * from vista_Proveedor")

        while (query.next()) {
            currentProveedor = Proveedor()

            currentProveedor.id = query.getInt("idProveedor")
            currentProveedor.nombre = query.getString("nombre")
            currentProveedor.contacto.id = query.getInt("idContacto")
            currentProveedor.contacto.correo = query.getString("correo")
            currentProveedor.contacto.telefono = query.getString("telefono")
            currentProveedor.contacto.direccion.id = query.getInt("idDireccion")
            currentProveedor.contacto.direccion.municipio = query.getString("municipio")
            currentProveedor.contacto.direccion.colonia = query.getString("colonia")
            currentProveedor.contacto.direccion.calle = query.getString("calle")
            currentProveedor.contacto.direccion.numero = query.getInt("numero")
            currentProveedor.contacto.direccion.codigoPostal = query.getString("codigoPostal")

            newList.add(currentProveedor)
        }

        return newList.toList()
    }


    /**
     * Inserts the supplier's data in the database
     * @param proveedor Proveedor to be added to the database
     */
    fun insertProveedor(proveedor: Proveedor): Boolean {
        val resultado =
            statement.executeUpdate(
                "execute insertProveedor '${proveedor.nombre}', '${proveedor.contacto.correo}', '${proveedor.contacto.telefono}', '${proveedor.contacto.direccion.municipio}', '${proveedor.contacto.direccion.colonia}', '${proveedor.contacto.direccion.calle}', ${proveedor.contacto.direccion.numero}, '${proveedor.contacto.direccion.codigoPostal}'"
            )

        return resultado > 0
    }


    /**
     * Updates the indicated supplier's entry in the database
     * @param proveedor Proveedor entry to be updated at the database
     */
    fun updateProveedor(proveedor: Proveedor): Boolean {
        val resultado = statement.executeUpdate(
            "execute updateProveedor ${proveedor.id}, '${proveedor.nombre}', ${proveedor.contacto.id}, '${proveedor.contacto.correo}', '${proveedor.contacto.telefono}', ${proveedor.contacto.direccion.id}, '${proveedor.contacto.direccion.municipio}', '${proveedor.contacto.direccion.colonia}', '${proveedor.contacto.direccion.calle}', ${proveedor.contacto.direccion.numero}, '${proveedor.contacto.direccion.codigoPostal}'"
        )

        return resultado > 0
    }


    /**
     * Deletes the indicated supplier's entry in the database
     * @param proveedor Proveedor to be deleted from the database
     */
    fun deleteProveedor(proveedor: Proveedor): Boolean {
        val resultado = statement.executeUpdate(
            "execute deleteProveedor ${proveedor.id}, ${proveedor.contacto.id}, ${proveedor.contacto.direccion.id}"
        )

        return resultado > 0
    }


    /**
     * Collects all the products provided by the supplier from the database
     * @param idProveedor ID of the Proveedor which products are going to be retrieved
     */
    fun getProductosProveedor(idProveedor: Int): List<Producto> {
        val newList = mutableListOf<Producto>()
        var currentProducto: Producto

        val query = statement.executeQuery("execute productosPorProveedor $idProveedor")

        while (query.next()) {
            currentProducto = Producto()

            currentProducto.id = query.getInt("idProducto")
            currentProducto.nombre = query.getString("nombre")
            currentProducto.precioReal = query.getDouble("precioReal")
            currentProducto.cantidadIVA = query.getDouble("cantidadIVA")
            currentProducto.precioVenta = query.getDouble("precioVenta")
            currentProducto.nombre = query.getString("descripcion")

            newList.add(currentProducto)
        }

        return newList.toList()
    }
}