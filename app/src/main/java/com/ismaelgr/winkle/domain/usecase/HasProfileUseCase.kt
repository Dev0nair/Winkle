package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed

class HasProfileUseCase(
    private val accountRepositoryFactory: AccountRepositoryNeed,
    private val profileRepositoryFactory: ProfileRepositoryNeed
) {

    fun execute(onLoad: (Boolean) -> Unit) {
        if (accountRepositoryFactory.isLogged()) {
            val myId = accountRepositoryFactory.getAccount().id
            profileRepositoryFactory.hasProfile(myId)
                .subscribe(onLoad, { onLoad(false) })
        } else {
            onLoad(false)
        }
    }
}