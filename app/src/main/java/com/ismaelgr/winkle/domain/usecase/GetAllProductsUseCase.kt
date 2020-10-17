package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed

class GetAllProductsUseCase(private val productRepositoryNeed: ProductRepositoryNeed) {

    fun execute(onSuccess: (List<Producto>) -> Unit, onError: (String) -> Unit) {
        productRepositoryNeed.getAllProducts(onSuccess, onError)
    }
}