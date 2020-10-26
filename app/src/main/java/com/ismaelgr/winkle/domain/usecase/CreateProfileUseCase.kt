package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CreateProfileUseCase(
    private val profileRepositoryNeed: ProfileRepositoryNeed,
    private val accountRepositoryNeed: AccountRepositoryNeed
) {

    fun execute(perfil: Perfil, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val account = accountRepositoryNeed.getAccount()
        perfil.idAccount = account.id
        perfil.email = account.email
        profileRepositoryNeed.createProfile(perfil)
            .doOnComplete(onSuccess)
            .doOnError { it.message.toString().run(onError) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}