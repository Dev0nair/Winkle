package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Cuenta

interface AccountRepositoryNeed {
    fun createAccount(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit)
    fun getAccount(): Cuenta
    fun isLogged(): Boolean
    fun login(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit)
    fun logout()
}