package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class GetProductsOfBasketUseCase(
    private val getMyCestaUseCase: GetMyCestaUseCase,
    private val productRepositoryNeed: ProductRepositoryNeed
) {

    private var productRepositoryListener: Disposable? = null

    fun execute(onSuccess: (List<Producto>, List<Cesta>) -> Unit, onError: (String) -> Unit) {
        getMyCestaUseCase.execute(
            onSuccess = { cesta ->
                productRepositoryListener = productRepositoryNeed.getProductsInfo(cesta.map { it.idProduct })
                    .doOnSuccess{ onSuccess(it, cesta) }
                    .doOnError { error -> error.message.toString().run(onError) }
                    .doOnComplete { onSuccess(emptyList(), emptyList()) }
                    .subscribe()
            },
            onError = onError
        )
    }

    fun dispose() {
        getMyCestaUseCase.dispose()
        productRepositoryListener?.dispose()
    }
}