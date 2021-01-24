package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class SelectProfileUseCase(
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed
) {

    private var disposable: Disposable? = null

    fun execute(idProfile: String = "", onSuccess: () -> Unit, onError: (String) -> Unit){
        if(idProfile.isEmpty()){
            val accId = accountRepositoryNeed.getAccount().id
            disposable = profileRepositoryNeed.getProfileFromAcc(accId)
                .subscribe(
                    { perfil -> profileRepositoryNeed.saveProfile(perfil).subscribe(onSuccess) },
                    { onError(it.message.toString()) },
                    { "No se ha encontrado un perfil en la cuenta (error a mano)" }
                )
        }
    }

    fun disposable() {
        disposable?.dispose()
    }
}