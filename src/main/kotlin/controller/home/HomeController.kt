package controller.home

import ProgramEscencials
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.cliente.Cliente
import model.cliente.ClienteDatabase
import model.cliente.getFullName
import model.fechaVenta.FechaVentaDatabase
import model.producto.Producto
import model.producto.ProductoDatabase
import model.productoVenta.ProductoVenta
import model.productoVenta.ProductoVentaDatabase
import model.reporte.Reporte
import model.reporte.ReporteDatabase
import model.venta.Venta
import model.venta.VentaDatabase

class HomeController {
    private var _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    var productsList: List<Producto> = emptyList()

    var clienteNamePair: MutableList<Pair<String, Cliente>> = mutableListOf()

    init {
        getClientList()
        getProductsList()
        resetState()
    }

    /**
     * Retrieves a list of clients from the database
     */
    private fun getClientList() {
        val newList = ClienteDatabase.getClienteList()

        _homeState.update { currentState ->
            currentState.copy(clientList = newList)
        }

        clienteNamePair = mutableListOf()
        _homeState.value.clientList.forEach { cliente ->
            clienteNamePair.add(Pair(cliente.getFullName(), cliente))
        }
    }

    /**
     * Retrieves a list of products from the database
     */
    private fun getProductsList() {
        productsList = ProductoDatabase.getProductList()
    }

    // Methods
    /**
     * Reinitialize the home state to default values
     * */
    fun resetState() {
        _homeState.update { currentState ->
            currentState.copy(
                selectedProductos = emptyList(),
                saleInfo = SaleInfo(0.0, 0.0, 0.0, 0.0),
                currentCliente = null,
                firstSalePromotion = ClientFirstSalePromotion()
            )
        }

        getClientList()
    }

    /**
     * Adds the passed product and quantity into the list of selected products.
     * Then updates the sale info based in the product contained in the selected products list
     * @param producto The product that will be added to the list
     * @param quantity The quantity selected for an individual product
     */
    fun onAddProductoClick(producto: Producto, quantity: Int?) {
        _homeState.update { currentState ->
            currentState.copy(selectedProductos = currentState.selectedProductos.addProducto(producto, quantity!!))
        }
        _homeState.update { currentState ->
            currentState.copy(
                selectedProductos = currentState.selectedProductos.actualizarPromocion(
                    clienteRegistrado = _homeState.value.currentCliente?.suscrito ?: false,
                    primeraCompra = _homeState.value.firstSalePromotion
                )
            )
        }
        updateSaleInfo(_homeState.value.selectedProductos)
    }

    private fun updateSaleInfo(selectedProductos: List<ProductoVenta>) {
        _homeState.value.saleInfo.update(
            selectedProductos,
            clienteRegistrado = _homeState.value.currentCliente?.suscrito ?: false,
            primeraCompra = _homeState.value.firstSalePromotion

        )
    }

    fun makeSale() {
        val empleado = ProgramEscencials.selectedEmpleado

        // Obtiene la FechaVenta
        val fechaVenta = FechaVentaDatabase.getFechaVenta()

        // Crear venta
        val idVenta = VentaDatabase.makeVenta(with(_homeState.value.saleInfo) {
            Venta(
                impRealVenta = subTotal,
                ivaVenta = subTotal * 0.16,
                impIvaVenta = incrementoIVA,
                desVenta = descuento,
                netoVenta = total,
                fechaVenta = fechaVenta,
                cliente = _homeState.value.currentCliente ?: Cliente(),
                empleado = empleado,
            )
        })

        var idProductoVenta: Int
        // Llenar la venta de la informacion de los productos vendidos (Crear los correspondientes ProductoVenta)
        _homeState.value.selectedProductos.forEach { productoVenta ->
            // Inserta ProductoVenta en la base de datos y obtiene su id
            idProductoVenta = ProductoVentaDatabase.createProductoVenta(productoVenta)

            // Crea un registro para la tabla DetalleProductoVenta
            VentaDatabase.createDetalleProductoVenta(idProductoVenta, idVenta)
        }

        // Actualiza el reporte diario
        if (ReporteDatabase.updateReporte(
                Reporte(
                    ventas = _homeState.value.selectedProductos.size,
                    ventasPromocion = _homeState.value.selectedProductos.count { it.descripcionPromocion != null },
                    ingresos = _homeState.value.saleInfo.total,
                    fechaVenta = fechaVenta
                )
            )
        ) resetState()
    }

    fun updateCurrentCliente(newClienteString: String) {
        val cliente = clienteNamePair.find { it.first == newClienteString }?.second ?: Cliente()

        _homeState.update { currentState ->
            currentState.copy(currentCliente = cliente)
        }

        defineFirstSalePromotion(cliente)
    }

    private fun defineFirstSalePromotion(cliente: Cliente) {
        var currentFirstSalePromotion = ClientFirstSalePromotion()

        val firstSale = cliente.cantidadCompras == 0

        if (firstSale) {
            currentFirstSalePromotion = if (cliente.suscrito) {
                ClientFirstSalePromotion(true, 0.1)
            } else {
                ClientFirstSalePromotion(true, 0.05)
            }
        }

        _homeState.update { currentState ->
            currentState.copy(firstSalePromotion = currentFirstSalePromotion)
        }

        _homeState.update { currentState ->
            currentState.copy(
                selectedProductos = currentState.selectedProductos.actualizarPromocion(
                    clienteRegistrado = _homeState.value.currentCliente?.suscrito ?: false,
                    primeraCompra = _homeState.value.firstSalePromotion
                )
            )
        }

        updateSaleInfo(_homeState.value.selectedProductos)
    }

    /**
     * Validates if the list of selected products is not empty
     */
    fun selectedProductsListIsNotEmpty() =
        _homeState.value.selectedProductos.isNotEmpty() && _homeState.value.currentCliente?.id != null


}


/*
* Controller Data class
*/
data class HomeState(
    var selectedProductos: List<ProductoVenta> = emptyList(),
    var saleInfo: SaleInfo = SaleInfo(0.0, 0.0, 0.0, 0.0),
    var clientList: List<Cliente> = emptyList(),
    var currentCliente: Cliente? = null,
    var firstSalePromotion: ClientFirstSalePromotion = ClientFirstSalePromotion()
)

data class SaleInfo(
    var subTotal: Double, var incrementoIVA: Double, var descuento: Double, var total: Double = subTotal - descuento
)

data class ClientFirstSalePromotion(
    var primeraCompra: Boolean = false, var descuento: Double? = null
)

/*
Helper methods
*/
/**
 * Add a product and quantity into the selected products list.
 * If the product is already in the list, the list element where is located will update its quantity.
 * If the product is not in the list, the product will be added to the list
 */
private fun List<ProductoVenta>.addProducto(
    producto: Producto, quantityValue: Int
): List<ProductoVenta> {
    var isInTheList = false
    val newList: MutableList<ProductoVenta> = this.toMutableList()

    // Validate if is already in the list, if so, update the element content
    newList.forEachIndexed { index, selectedProductos ->
        if (producto.id == selectedProductos.producto.id) {
            isInTheList = true
            newList[index] = ProductoVenta(
                cantidad = quantityValue, producto = producto, descripcionPromocion = producto.getDescripcion()
            )
        }
    }

    if (!isInTheList) newList.add(
        ProductoVenta(
            cantidad = quantityValue, producto = producto, descripcionPromocion = producto.getDescripcion()
        )
    )

    return newList.toList()
}

private fun List<ProductoVenta>.actualizarPromocion(
    clienteRegistrado: Boolean, primeraCompra: ClientFirstSalePromotion
): List<ProductoVenta> {
    val newList = mutableListOf<ProductoVenta>()

    // Lasciate ogni speranza, voi ch'entrate
    this.forEach { productoVenta ->
        productoVenta.let {
            it.descripcionPromocion = if (clienteRegistrado) {
                if (it.producto.promocion?.disponibilidad == true) productoVenta.producto.getDescripcion()
                else if (primeraCompra.primeraCompra) "Primer compra (${
                    primeraCompra.descuento!!.times(100).toInt()
                } %)"
                else null
            } else {
                if (primeraCompra.primeraCompra) "Primer compra (${primeraCompra.descuento!!.times(100).toInt()} %)"
                else null
            }
            it.precioVenta = (it.subtotal + it.cantidadIVA).times(
                1.0 - if (clienteRegistrado) {
                    if (it.producto.promocion?.disponibilidad == true) it.producto.promocion!!.descuento
                    else if (primeraCompra.primeraCompra) primeraCompra.descuento!!
                    else 0.0
                } else {
                    if (primeraCompra.primeraCompra) primeraCompra.descuento!!
                    else 0.0
                }
            )
        }

        newList.add(productoVenta)
    }

    return newList.toList()
}

/**
 * Navigates within selectedProductosList to calculate the SaleInfo's subtotal, descuento and total
 */
private fun SaleInfo.update(
    selectedProductosList: List<ProductoVenta>, clienteRegistrado: Boolean, primeraCompra: ClientFirstSalePromotion
) {
    var newSubtotal = 0.0
    var newIVA = 0.0
    var newDescuento = 0.0

    selectedProductosList.forEach { selectedProducto ->
        newSubtotal += selectedProducto.subtotal
        newIVA += selectedProducto.cantidadIVA
        newDescuento += (selectedProducto.subtotal + selectedProducto.cantidadIVA).times(
            if (clienteRegistrado) {
                if (selectedProducto.producto.promocion?.disponibilidad == true) selectedProducto.producto.promocion!!.descuento
                else if (primeraCompra.primeraCompra) primeraCompra.descuento!!
                else 0.0
            } else {
                if (primeraCompra.primeraCompra) primeraCompra.descuento!!
                else 0.0
            }
        )
    }

    this.subTotal = newSubtotal
    this.incrementoIVA = newIVA
    this.descuento = newDescuento
    this.total = newSubtotal + newIVA - newDescuento
}

private fun Producto.getDescripcion(): String? = with(this.promocion) {
    if (this?.disponibilidad == true) this.description else null
}