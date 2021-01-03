package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.Disposable

class GetAllProductsExceptMineUseCase(
    private val productRepositoryNeed: ProductRepositoryNeed,
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed
) {

    private var listener: Disposable? = null

    fun execute(onSuccess: (List<Producto>) -> Unit, onError: (String) -> Unit) {
        profileRepositoryNeed.getProfile(accountRepositoryNeed.getAccount().id).subscribe(
            { profile ->
                listener = productRepositoryNeed.getAllProductsExcept(profile.id)
                    .subscribe(onSuccess)
            },
            { onError(it.message.toString()) },
            { onSuccess(emptyList()) }
        )
    }

    fun dispose() {
        listener?.dispose()
    }
}