package model.proveedor

import model.contacto.Contacto
import model.contacto.ContactoTestList
import model.producto.Producto
import model.producto.ProductoTestList
import kotlin.random.Random

data class Proveedor(
    var id: Long? = null,
    var nombre: String = "",
    var contacto: Contacto = Contacto(),
    var productos: List<Producto> = emptyList()
)


val ProveedorTestList = listOf(
    Proveedor(
        1, "Proveedor 1", ContactoTestList[0], listOf(
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)]
        )
    ),
    Proveedor(
        2, "Proveedor 2", ContactoTestList[1], listOf(
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)]
        )
    ),
    Proveedor(
        3, "Proveedor 3", ContactoTestList[2], listOf(
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)]
        )
    ),
    Proveedor(
        4, "Proveedor 4", ContactoTestList[3], listOf(
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)]
        )
    ),
    Proveedor(
        5, "Proveedor 5", ContactoTestList[4], listOf(
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)],
            ProductoTestList[Random.nextInt(0, 39)]
        )
    ),
)