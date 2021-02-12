package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.Disposable

class GetAllProductsExceptMineUseCase(
    private val productRepositoryNeed: ProductRepositoryNeed,
    private val getActualProfileUseCase: GetActualProfileUseCase
) {

    private var listener: Disposable? = null

    fun execute(onSuccess: (List<Producto>) -> Unit, onError: (String) -> Unit) {
        getActualProfileUseCase.execute(
            onSuccess = { profile ->
                listener = productRepositoryNeed.getAllProductsExcept(profile.id)
                    .subscribe(onSuccess)
            },
            onError = onError
        )
    }

    fun dispose() {
        listener?.dispose()
    }
}