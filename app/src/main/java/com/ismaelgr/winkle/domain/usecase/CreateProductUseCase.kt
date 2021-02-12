package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed

class CreateProductUseCase(private val productRepositoryNeed: ProductRepositoryNeed) {

    fun execute(vararg producto: Producto, onSuccess: () -> Unit, onError: (String) -> Unit) {
        productRepositoryNeed.createProduct(*producto)
            .subscribe(onSuccess, {onError(it.message.toString())})
    }
}