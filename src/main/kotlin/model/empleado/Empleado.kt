package model.empleado

import Database
import model.contacto.Contacto
import model.detalleVentaProducto.DetalleVentaProducto
import model.detalleVentaProducto.DetalleVentaProductoDatabase
import model.fechaVenta.FechaVenta
import model.productoVenta.ProductoVenta
import model.sucursal.Sucursal
import model.venta.Venta
import util.UserType
import util.toByte
import util.toUserType
import java.sql.Statement

data class Empleado(
    // Primary key
    var id: Int? = null,

    // Attributes
    var primerNombre: String = "",
    var segundoNombre: String = "",
    var apellido: String = "",
    var puesto: UserType = UserType.CASHIER,
    var cantidadVentas: Int = 0,

    // Foreign key
    var contacto: Contacto = Contacto(),
    var sucursal: Sucursal = Sucursal(),

    // List of contents filled post retrieving
    var listaVentas: List<DetalleVentaProducto> = emptyList()
)

// Empleado extension functions
fun Empleado.getSueldo() = if (this.puesto == UserType.ADMINISTRATOR) 15000.0 else 5000.0

fun Empleado.getFullName() = if(id == null) "Empleado no disponible" else "$primerNombre $segundoNombre $apellido"



object EmpleadoDatabase {
    private var statement: Statement = Database.connection.createStatement()

    fun getEmpleado(idEmpleado: Int): Empleado {
        var empleado = Empleado()

        val query = statement.executeQuery("select * from vista_Empleado where idEmpleado = $idEmpleado")

        while(query.next()) {
            with(query) {
                empleado = Empleado(
                    id = getInt("idEmpleado"),
                    primerNombre = getString("primerNombre"),
                    segundoNombre = getString("segundoNombre"),
                    apellido = getString("apellido")
                )
            }
        }

        return empleado
    }

    /**
     * Collects all the employees stored in the database
     */
    fun getEmpleadoList(): List<Empleado> {
        val newList = mutableListOf<Empleado>()
        var currentEmpleado: Empleado

        val query = statement.executeQuery("select * from vista_Empleado")

        while (query.next()) {
            currentEmpleado = Empleado()

            currentEmpleado.id = query.getInt("idEmpleado")
            currentEmpleado.primerNombre = query.getString("primerNombre")
            currentEmpleado.segundoNombre = query.getString("segundoNombre")
            currentEmpleado.apellido = query.getString("apellido")
            currentEmpleado.puesto = query.getByte("puesto").toUserType()
            currentEmpleado.cantidadVentas = query.getInt("ventas")
            currentEmpleado.contacto.id = query.getInt("idContacto")
            currentEmpleado.contacto.correo = query.getString("correo")
            currentEmpleado.contacto.telefono = query.getString("telefono")
            currentEmpleado.sucursal.id = query.getInt("idSucursal")
            currentEmpleado.sucursal.name = query.getString("nombreSucursal")

            newList.add(currentEmpleado)
        }

        return newList.toList()
    }


    /**
     * Inserts the employee's data in the database
     * @param empleado Empleado to be added to the database
     */
    fun insertEmpleado(empleado: Empleado): Boolean {
        val resultado =
            statement.executeUpdate(
                "execute insertEmpleado '${empleado.primerNombre}', '${empleado.segundoNombre}', '${empleado.apellido}', ${empleado.puesto.toByte()}, '${empleado.contacto.correo}', '${empleado.contacto.telefono}', ${empleado.sucursal.id}"
            )

        return resultado > 0
    }


    /**
     * Updates the indicated employee's entry in the database
     * @param empleado Empleado entry to be updated at the database
     */
    fun updateEmpleado(empleado: Empleado): Boolean {
        val resultado = statement.executeUpdate(
            "execute updateEmpleado ${empleado.id}, '${empleado.primerNombre}', '${empleado.segundoNombre}', '${empleado.apellido}', ${empleado.puesto.toByte()}, ${empleado.contacto.id}, '${empleado.contacto.correo}', '${empleado.contacto.telefono}', ${empleado.sucursal.id}"
        )

        return resultado > 0
    }


    /**
     * Deletes the indicated employee's entry in the database
     * @param empleado Empleado to be deleted from the database
     */
    fun deleteEmpleado(empleado: Empleado): Boolean {
        val resultado = statement.executeUpdate("execute deleteEmpleado ${empleado.id}, ${empleado.contacto.id}")

        return resultado > 0
    }


    /**
     * Collects all sales of the employee's from the database
     * @param idEmpleado ID of the Empleado which purchases are going to be retrieved
     */
    fun getVentasEmpleado(idEmpleado: Int): List<DetalleVentaProducto> {
        val newList = mutableListOf<DetalleVentaProducto>()
        var newProductosList: List<ProductoVenta>
        var currentVenta: Venta

        val query = statement.executeQuery("execute ventasEmpleado $idEmpleado")

        while (query.next()) {
            currentVenta = Venta()

            with(query) {
                currentVenta = Venta(
                    id = getInt("idVenta"),
                    impRealVenta = getDouble("importeReal"),
                    ivaVenta = getDouble("ivaVenta"),
                    impIvaVenta = getDouble("importeConIVA"),
                    desVenta = getDouble("descuento"),
                    netoVenta = getDouble("importeNeto"),
                    FechaVenta(
                        dia = getInt("dia"),
                        mes = getInt("mes"),
                        anio = getInt("anio")
                    )
                )
            }

            newProductosList = DetalleVentaProductoDatabase.getProductosPorVentaPorEmpleado(currentVenta.id!!, idEmpleado)

            newList.add(DetalleVentaProducto(venta = currentVenta, productos = newProductosList))
        }

        return newList.toList()
    }
}