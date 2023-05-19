package model.venta

import Database
import model.cliente.Cliente
import model.cliente.ClienteDatabase
import model.detalleVentaProducto.DetalleVentaProducto
import model.detalleVentaProducto.DetalleVentaProductoDatabase
import model.empleado.Empleado
import model.empleado.EmpleadoDatabase
import model.fechaVenta.FechaVenta
import model.productoVenta.ProductoVenta

data class Venta(
    // Primary key
    var id: Int? = null,

    // Atributes
    var impRealVenta: Double = 0.0,
    var ivaVenta: Double = 0.0,
    var impIvaVenta: Double = 0.0,
    var desVenta: Double = 0.0,
    var netoVenta: Double = 0.0,

    // Foreign key
    var fechaVenta: FechaVenta = FechaVenta(),
    var cliente: Cliente = Cliente(),
    var empleado: Empleado = Empleado()
)


object VentaDatabase {
    private val statement = Database.connection.createStatement()

    fun getVentaList(): List<DetalleVentaProducto> {
        val newList = mutableListOf<DetalleVentaProducto>()
        var newProductosList: List<ProductoVenta>
        var currentVenta: Venta
        var currentCliente: Cliente
        var currentEmpleado: Empleado

        val query = statement.executeQuery("select * from vista_Venta")

        while (query.next()) {
            with(query) {
                currentCliente = ClienteDatabase.getCliente(getInt("idCliente"))
                currentEmpleado = EmpleadoDatabase.getEmpleado(getInt("idEmpleado"))

                currentVenta = Venta(
                    id = getInt("idVenta"),
                    impRealVenta = getDouble("importeReal"),
                    ivaVenta = getDouble("ivaVenta"),
                    impIvaVenta = getDouble("importeConIVA"),
                    desVenta = getDouble("descuento"),
                    netoVenta = getDouble("importeNeto"),
                    fechaVenta = FechaVenta(
                        id = null,
                        dia = getInt("dia"),
                        mes = getInt("mes"),
                        anio = getInt("anio")
                    ),
                    empleado = currentEmpleado,
                    cliente = currentCliente
                )
            }

            newProductosList = DetalleVentaProductoDatabase.getProductosPorVenta(currentVenta.id!!)

            newList.add(DetalleVentaProducto(venta = currentVenta, productos = newProductosList))
        }

        return newList.toList()
    }

    fun makeVenta(venta: Venta): Int {
        statement.executeUpdate(
            "execute efectuarVenta ${venta.impRealVenta}, ${venta.ivaVenta}, ${venta.impIvaVenta}, ${venta.desVenta}, ${venta.netoVenta}, ${venta.fechaVenta.id}, ${venta.cliente.id}, ${venta.empleado.id}"
        )

        val query = statement.executeQuery("select idVenta from Venta where idVenta = IDENT_CURRENT('Venta')")

        var idVenta = 0
        while (query.next()) {
            idVenta = query.getInt("idVenta")
        }

        return idVenta
    }

    fun createDetalleProductoVenta(idProductoVenta: Int, idVenta: Int): Boolean {
        val resultado = statement.executeUpdate(
            "execute createDetalleProductoVenta $idProductoVenta, $idVenta"
        )

        return resultado > 0
    }
}