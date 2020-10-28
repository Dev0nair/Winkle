package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class GetMyCestaUseCase(
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed,
    private val cestaRepositoryNeed: CestaRepositoryNeed
) {

    private var listenerPerfil: Disposable? = null
    private var listenerCesta: Disposable? = null

    fun execute(onSuccess: (Cesta) -> Unit, onError: (String) -> Unit){
        val myAccId = accountRepositoryNeed.getAccount().id

        listenerPerfil = profileRepositoryNeed.getProfileFromAcc(myAccId)
            .doOnSuccess { perfil ->
                listenerCesta = cestaRepositoryNeed.getCesta(perfil.id)
                    .doOnSuccess(onSuccess)
                    .doOnError { it.message.toString().run(onError) }
                    .subscribe()
            }
            .subscribe()
    }

    fun dispose() {
        listenerPerfil?.dispose()
        listenerCesta?.dispose()
    }
}