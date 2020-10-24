package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class GetMyProductsUseCase(
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed,
    private val productRepositoryNeed: ProductRepositoryNeed
) {
    private var listener: Disposable? = null

    fun execute(onSuccess: (List<Producto>) -> Unit, onError: (error: String) -> Unit) {
        val myAccId = accountRepositoryNeed.getAccount().id

        profileRepositoryNeed.getProfileFromAcc(myAccId,
            onSuccess = { perfil ->
                val myPerfilId = perfil.id
                productRepositoryNeed.getProductsOf(myPerfilId)
                    .doOnSuccess(onSuccess)
                    .doOnError { error -> onError(error.message.toString()) }
                    .subscribe()
            },
            onError = { onError(it) }
        )
    }

    fun dispose() {
        listener?.dispose()
    }
}