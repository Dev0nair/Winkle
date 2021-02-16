package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class SaveProductUseCase(
    private val productRepositoryNeed: ProductRepositoryNeed
) {

    private var disposable: Disposable? = null

    fun execute(producto: Producto, onComplete: () -> Unit, onError: (String) -> Unit) {
        disposable = productRepositoryNeed.saveProduct(producto)
            .subscribe(onComplete, { onError(it.message.toString()) })
    }

    fun dispose() {
        disposable?.dispose()
    }
}