package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed

class GetMyProductsUseCase(
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed,
    private val productRepositoryNeed: ProductRepositoryNeed
) {

    fun execute(onSuccess: (List<Producto>) -> Unit, onError: (error: String) -> Unit) {
        val myAccId = accountRepositoryNeed.getAccount().id

        profileRepositoryNeed.getProfileFromAcc(myAccId,
            onSuccess = { perfil ->
                val myPerfilId = perfil.id
                productRepositoryNeed.getProductsOf(myPerfilId, onSuccess, onError)
            },
            onError = { onError(it) }
        )
    }
}