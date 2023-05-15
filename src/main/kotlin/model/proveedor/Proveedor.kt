package model.proveedor

import model.contacto.Contacto
import model.producto.Producto

data class Proveedor(
    var id: Long? = null,
    var nombre: String = "",
    var contacto: Contacto = Contacto(),
    var productos: List<Producto> = emptyList()
)