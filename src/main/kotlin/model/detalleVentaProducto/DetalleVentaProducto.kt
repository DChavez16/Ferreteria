package model.detalleVentaProducto

import model.productoVenta.ProductoVenta
import model.venta.Venta

data class DetalleVentaProducto(
    // Foreign key
    var venta: Venta = Venta(),
    var productos: List<ProductoVenta> = emptyList()
)