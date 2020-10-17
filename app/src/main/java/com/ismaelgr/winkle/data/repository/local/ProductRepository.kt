package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed

class ProductRepository: ProductRepositoryNeed {

    private val products = listOf(
        Producto("1", "Portatil HP i7 16RAM RTX 2070", "Esta nuevo señores lo digo enserio...", "", 1400F, "1", true),
        Producto("1", "Peine bien fachero", "Esta nuevo señores lo digo enserio...", "", 12F, "2", true),
        Producto("1", "Apuntes de clase de informatica", "Esta nuevo señores lo digo enserio...", "", 10F, "1", true),
        Producto("1", "Ajedrez seminuevo", "Esta nuevo señores lo digo enserio...", "", 4.5F, "2", true),
        Producto("1", "Pixel 5 de alemania", "Esta nuevo señores lo digo enserio...", "", 650F, "2", true),
        Producto("1", "Cenicero de mi hijo", "Esta nuevo señores lo digo enserio...", "", 2.25F, "1", true),
        Producto("1", "Botella de vodka a medias", "Esta nuevo señores lo digo enserio...", "", 3.5F, "1", true)
    )

    override fun getProductsOf(
        idProfile: String,
        onSuccess: (List<Producto>) -> Unit,
        onError: (String) -> Unit
    ) {
        val productsOfProfile = products.filter{ producto -> producto.vendedorId == idProfile }
        onSuccess(productsOfProfile)
    }

    override fun getAllProducts(onSuccess: (List<Producto>) -> Unit, onError: (String) -> Unit) {
        onSuccess(products)
    }

    override fun getProductInfo(
        idProducto: String,
        onSuccess: (Producto) -> Unit,
        onError: (String) -> Unit
    ) {
        val producto: Producto? = products.find { product -> product.id == idProducto }
        if(producto != null){
            onSuccess(producto)
        } else {
            onError("There is no product with id $idProducto")
        }
    }
}