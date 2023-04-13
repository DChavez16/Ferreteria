package data.util

class NavigationOptions(userType: UserType) {
    val navigationList: List<NavigationOption> =
        if (userType == UserType.CASHIER) listOf(
            NavigationOption("icons/inicio.png", "Inicio", NavigationOptionsCodes.INICIO),
            NavigationOption("icons/venta.png", "Ventas", NavigationOptionsCodes.VENTAS)
        )
        else listOf(
            NavigationOption("icons/inicio.png", "Inicio", NavigationOptionsCodes.INICIO),
            NavigationOption("icons/venta.png", "Ventas", NavigationOptionsCodes.VENTAS),
            NavigationOption("icons/producto.png", "Productos", NavigationOptionsCodes.PRODUCTO),
            NavigationOption("icons/promocion.png", "Promociones", NavigationOptionsCodes.PROMOCION),
            NavigationOption("icons/proveedor.png", "Proveedores", NavigationOptionsCodes.PROVEEDOR),
            NavigationOption("icons/reporte.png", "Reportes", NavigationOptionsCodes.REPORTE),
            NavigationOption("icons/empleado.png", "Empleados", NavigationOptionsCodes.EMPLEADO),
            NavigationOption("icons/sucursal.png", "Sucursales", NavigationOptionsCodes.SUCURSAL),
            NavigationOption("icons/cliente.png", "Clientes", NavigationOptionsCodes.CLIENTE)
        )
}

data class NavigationOption(
    val imagePath: String,
    val name: String,
    val code: NavigationOptionsCodes
)

enum class NavigationOptionsCodes {
    INICIO,
    VENTAS,
    PRODUCTO,
    PROMOCION,
    PROVEEDOR,
    REPORTE,
    EMPLEADO,
    SUCURSAL,
    CLIENTE
}