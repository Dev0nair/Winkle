package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Cuenta
import io.reactivex.rxjava3.core.Completable

interface AccountRepositoryNeed {
    fun createAccount(email: String, pass: String): Completable
    fun getAccount(): Cuenta
    fun isLogged(): Boolean
    fun login(email: String, pass: String): Completable
    fun logout()
}