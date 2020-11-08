package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class GetProductosMiCesta(
    private val getMyCestaUseCase: GetMyCestaUseCase,
    private val productRepositoryNeed: ProductRepositoryNeed
) {

    private var productRepositoryListener: Disposable? = null

    fun execute(onSuccess: (List<Producto>) -> Unit, onError: (String) -> Unit) {
        getMyCestaUseCase.execute(
            onSuccess = { cesta ->
                productRepositoryListener = productRepositoryNeed.getProductsInfo(cesta.products)
                    .doOnSuccess(onSuccess)
                    .doOnError { error -> error.message.toString().run(onError) }
                    .doOnComplete { onSuccess(emptyList()) }
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