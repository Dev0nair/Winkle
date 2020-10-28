package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class GetAllProductsUseCase(private val productRepositoryNeed: ProductRepositoryNeed) {

    private var listener: Disposable? = null

    fun execute(onSuccess: (List<Producto>) -> Unit, onError: (String) -> Unit) {
        listener = productRepositoryNeed.getAllProducts()
            .doOnSuccess(onSuccess)
            .doOnError { error -> onError(error.message.toString()) }
            .doOnComplete { onSuccess(emptyList()) }
            .subscribe()
    }

    fun dispose() {
        listener?.dispose()
    }
}