package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed

class SaveProductUseCase(
    private val productRepositoryNeed: ProductRepositoryNeed
) {
    fun execute(producto: Producto, onComplete: () -> Unit, onError: (String) -> Unit) {
        productRepositoryNeed.saveProduct(producto)
            .subscribe(onComplete, { onError(it.message.toString()) })
    }
}