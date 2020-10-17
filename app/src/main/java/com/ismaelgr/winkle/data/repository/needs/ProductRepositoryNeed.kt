package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Producto

interface ProductRepositoryNeed {
    fun getProductsOf(idProfile: String, onSuccess: (List<Producto>) -> Unit, onError: (String) -> Unit)
    fun getAllProducts(onSuccess: (List<Producto>) -> Unit, onError: (String) -> Unit)

    fun getProductInfo(idProducto: String, onSuccess: (Producto) -> Unit, onError: (String) -> Unit)
}