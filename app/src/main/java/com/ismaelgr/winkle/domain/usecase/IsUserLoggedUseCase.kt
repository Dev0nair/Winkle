package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed

class IsUserLoggedUseCase(private val accountRepositoryNeed: AccountRepositoryNeed) {

    fun execute(onLoad: (Boolean) -> Unit) {
        accountRepositoryNeed.isLogged().run(onLoad)
    }
}