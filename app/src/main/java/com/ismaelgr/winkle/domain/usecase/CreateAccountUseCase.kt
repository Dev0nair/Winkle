package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed

class CreateAccountUseCase(private val accountRepository: AccountRepositoryNeed) {

    fun execute(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        accountRepository.createAccount(email, pass, onSuccess, onError)
    }
}