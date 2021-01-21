package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class GetActualProfileUseCase(
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed
) {

    private var disposable: Disposable? = null

    fun execute(onSuccess: (Perfil) -> Unit, onError: (String) -> Unit) {
        // Aqui buscamos si tenemos un perfil guardado (que deberíamos). En caso de no tenerlo, cogeremos el primer perfil asignado a nuestra cuenta
        profileRepositoryNeed.getSavedProfile()
            .subscribe(
                onSuccess,
                { error -> onError(error.message.toString()) },
                { loadProfileFromAcc(onSuccess, onError) }
            )
    }

    private fun loadProfileFromAcc(onSuccess: (Perfil) -> Unit, onError: (String) -> Unit) {
        val idAccount = accountRepositoryNeed.getAccount().id
        profileRepositoryNeed.getProfile(idAccount)
            .subscribe(
                onSuccess,
                { onError(it.message.toString()) })
    }

    fun dispose() {
        disposable?.dispose()
    }
}