package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GetMyProductsUseCase(
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed,
    private val productRepositoryNeed: ProductRepositoryNeed
) {
    private var listenerPerfil: Disposable? = null
    private var listenerProductos: Disposable? = null

    fun execute(onSuccess: (List<Producto>) -> Unit, onError: (error: String) -> Unit) {
        val myAccId = accountRepositoryNeed.getAccount().id

        listenerPerfil = profileRepositoryNeed.getProfileFromAcc(myAccId)
            .doOnSuccess { perfil ->
                listenerProductos = productRepositoryNeed.getProductsOf(perfil.id)
                    .doOnSuccess(onSuccess)
                    .doOnError { it.message.toString().run(onError) }
                    .doOnComplete { onSuccess(emptyList()) }
                    .subscribe()
            }
            .subscribe()
    }

    fun dispose() {
        listenerPerfil?.dispose()
        listenerProductos?.dispose()
    }
}