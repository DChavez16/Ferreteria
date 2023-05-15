package model.cliente

import Database
import model.contacto.Contacto
import model.detalleVentaProducto.DetalleVentaProducto
import java.sql.Statement

data class Cliente(
    // Primary key
    var id: Long? = null,

    // Atributes
    var nombre: String = "",
    var suscrito: Boolean = false,
    var contacto: Contacto = Contacto(),    // Foreign key
    var cantidadCompras: Int = 0,

    // List of contents filled post retrieving
    var listaCompras: List<DetalleVentaProducto> = emptyList()
)


object ClienteDatabase {
    private var statement: Statement = Database.connection.createStatement()

    fun getClientList(): List<Cliente> {
        val newList = mutableListOf<Cliente>()
        val currentCliente = Cliente()

        val query = statement.executeQuery("select * from vista_Cliente")

        while(query.next()) {
            currentCliente.id = query.getInt("idCliente").toLong()
            currentCliente.nombre = query.getString("nombre")
            currentCliente.suscrito = when(query.getByte("suscripcion")) {
                0.toByte() -> false
                else -> true
            }
            currentCliente.cantidadCompras = query.getInt("compras")
            currentCliente.contacto.id = query.getInt("idContacto").toLong()
            currentCliente.contacto.correo = query.getString("correo")
            currentCliente.contacto.telefono = query.getString("telefono")

            newList.add(currentCliente)
        }

        return newList.toList()
    }
}


//// TODO Change this temporal line when the database is implemented
//val statement = Database.connection.createStatement()
//val productsListQuery = statement.executeQuery("SELECT * FROM Producto")
//val productTemp = Producto()
//
//while (productsListQuery.next()) {
//    productTemp.id = productsListQuery.getInt("idProducto").toLong()
//    productTemp.nombre = productsListQuery.getString("nomProducto")
//    productTemp.precioReal = productsListQuery.getDouble("preRealProducto")
//    productTemp.cantidadIVA = productsListQuery.getDouble("cantIVAProducto")
//    productTemp.precioVenta = productsListQuery.getDouble("preVenProducto")
//    productTemp.descripcion = productsListQuery.getString("desProducto")
//    productTemp.proveedor.id = productsListQuery.getInt("idProveedor").toLong()
//
//    productsList.add(productTemp)
//}