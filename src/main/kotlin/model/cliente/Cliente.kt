package model.cliente

import Database
import model.contacto.Contacto
import model.detalleVentaProducto.DetalleVentaProducto
import model.detalleVentaProducto.DetalleVentaProductoDatabase
import model.empleado.Empleado
import model.empleado.EmpleadoDatabase
import model.fechaVenta.FechaVenta
import model.productoVenta.ProductoVenta
import model.venta.Venta
import util.toBoolean
import util.toByte
import java.sql.Statement

data class Cliente(
    // Primary key
    var id: Int? = null,

    // Atributes
    var primerNombre: String = "",
    var segundoNombre: String = "",
    var apellido: String = "",
    var suscrito: Boolean = false,
    var contacto: Contacto = Contacto(),    // Foreign key
    var cantidadCompras: Int = 0,

    // List of contents filled post retrieving
    var listaCompras: List<DetalleVentaProducto> = emptyList()
)

// Extension functions
fun Cliente.getFullName() = if(id == null) "Cliente no disponible" else "$primerNombre $segundoNombre $apellido"


object ClienteDatabase {
    private var statement: Statement = Database.connection.createStatement()

    fun getCliente(idCliente: Int): Cliente {
        var cliente = Cliente()

        val query = statement.executeQuery("select * from vista_Cliente where idCliente = $idCliente")

        while(query.next()) {
            with(query) {
                cliente = Cliente(
                    id = getInt("idCliente"),
                    primerNombre = getString("primerNombre"),
                    segundoNombre = getString("segundoNombre"),
                    apellido = getString("apellido"),
                    suscrito = getByte("suscrito").toBoolean(),
                    cantidadCompras = getInt("compras")
                )
            }
        }

        return cliente
    }

    /**
     * Collects all the clients stored in the database
     */
    fun getClienteList(): List<Cliente> {
        val newList = mutableListOf<Cliente>()
        var currentCliente: Cliente

        val query = statement.executeQuery("select * from vista_Cliente")

        while (query.next()) {
            currentCliente = Cliente()

            with(query) {
                currentCliente = Cliente(
                    id = query.getInt("idCliente"),
                    primerNombre = getString("primerNombre"),
                    segundoNombre = getString("segundoNombre"),
                    apellido = getString("apellido"),
                    suscrito = getByte("suscrito").toBoolean(),
                    cantidadCompras = getInt("compras"),
                    contacto = Contacto(
                        id = getInt("idContacto"),
                        correo = getString("correo"),
                        telefono = getString("telefono")
                    )
                )
            }

            newList.add(currentCliente)
        }

        return newList.toList()
    }


    /**
     * Inserts the client's data in the database
     * @param cliente Cliente to be added to the database
     */
    fun insertCliente(cliente: Cliente): Boolean {
        val resultado =
            statement.executeUpdate("execute insertClient '${cliente.primerNombre}', '${cliente.segundoNombre}', '${cliente.apellido}', ${cliente.suscrito.toByte()}, '${cliente.contacto.correo}', '${cliente.contacto.telefono}'")

        return resultado > 0
    }


    /**
     * Updates the indicated client's entry in the database
     * @param cliente Cliente entry to be updated at the database
     */
    fun updateCliente(cliente: Cliente): Boolean {
        val resultado = statement.executeUpdate(
            "execute updateClient " + "${cliente.id}, '${cliente.primerNombre}', '${cliente.segundoNombre}', '${cliente.apellido}', ${cliente.suscrito.toByte()}, ${cliente.contacto.id}, '${cliente.contacto.correo}', '${cliente.contacto.telefono}'"
        )

        return resultado > 0
    }


    /**
     * Deletes the indicated client's entry in the database
     * @param cliente Cliente to be deleted from the database
     */
    fun deleteCliente(cliente: Cliente): Boolean {
        val resultado = statement.executeUpdate("execute deleteClient ${cliente.id}, ${cliente.contacto.id}")

        return resultado > 0
    }


    /**
     * Collects all purchases of the client's from the database
     * @param idCliente ID of the Cliente which purchases are going to be retrieved
     */
    fun getComprasCliente(idCliente: Int): List<DetalleVentaProducto> {
        val newList = mutableListOf<DetalleVentaProducto>()
        var newProductosList: List<ProductoVenta>
        var currentVenta: Venta
        var currentEmpleado: Empleado

        val query = statement.executeQuery("execute comprasCliente $idCliente")

        while (query.next()) {
            currentVenta = Venta()

            with(query) {
                currentEmpleado = EmpleadoDatabase.getEmpleado(getInt("idEmpleado"))

                currentVenta = Venta(
                    id = getInt("idVenta"),
                    impRealVenta = getDouble("importeReal"),
                    ivaVenta = getDouble("ivaVenta"),
                    impIvaVenta = getDouble("importeConIVA"),
                    desVenta = getDouble("descuento"),
                    netoVenta = getDouble("importeNeto"),
                    FechaVenta(
                        dia = getInt("dia"), mes = getInt("mes"), anio = getInt("anio")
                    ),
                    empleado = currentEmpleado
                )
            }

            newProductosList =
                DetalleVentaProductoDatabase.getProductosPorCompraPorCliente(currentVenta.id!!, idCliente)

            newList.add(DetalleVentaProducto(venta = currentVenta, productos = newProductosList))
        }

        return newList.toList()
    }
}