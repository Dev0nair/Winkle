package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Cuenta
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed

class AccountRepository : AccountRepositoryNeed {

    private var logged = true
    private val cuenta = Cuenta(
        id = "123456789",
        username = "Ismael González",
        email = "correo_prueba@gmail.com",
        phone = "654725348",
        image = ""
    )

    override fun createAccount(
        email: String,
        pass: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        onSuccess()
    }

    override fun getAccount(): Cuenta = cuenta

    override fun isLogged(): Boolean = logged

    override fun login(
        email: String,
        pass: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        logged = true
        onSuccess()
    }

    override fun logout() {
    }
}