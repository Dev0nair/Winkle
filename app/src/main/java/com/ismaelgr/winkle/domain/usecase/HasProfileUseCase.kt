package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HasProfileUseCase(
    private val accountRepositoryFactory: AccountRepositoryNeed,
    private val profileRepositoryFactory: ProfileRepositoryNeed
) {

    fun execute(onLoad: (Boolean) -> Unit) {
        if (accountRepositoryFactory.isLogged()) {
            val myId = accountRepositoryFactory.getAccount().id
            profileRepositoryFactory.hasProfile(myId)
                .doOnSuccess(onLoad)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        } else {
            onLoad(false)
        }
    }
}