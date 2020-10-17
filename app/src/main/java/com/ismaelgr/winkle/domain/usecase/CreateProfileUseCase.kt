package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed

class CreateProfileUseCase(
    private val profileRepositoryNeed: ProfileRepositoryNeed,
    private val accountRepositoryNeed: AccountRepositoryNeed
) {

    fun execute(perfil: Perfil, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val account = accountRepositoryNeed.getAccount()
        perfil.id = account.id
        perfil.email = account.email
        profileRepositoryNeed.createProfile(perfil, onSuccess, onError)
    }
}