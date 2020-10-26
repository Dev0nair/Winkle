package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginUseCase(private val accountRepository: AccountRepositoryNeed) {

    fun execute(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        accountRepository.login(email, pass)
            .doOnComplete(onSuccess)
            .doOnError { it.message.toString().run(onError) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}