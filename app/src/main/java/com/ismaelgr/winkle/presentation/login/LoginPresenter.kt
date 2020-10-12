package com.ismaelgr.winkle.presentation.login

import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.domain.usecase.HasProfileUseCase
import com.ismaelgr.winkle.domain.usecase.LoginUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class LoginPresenter(
    private val login: LoginContract.View,
    private val loginUseCase: LoginUseCase,
    private val hasProfileUseCase: HasProfileUseCase
) :
    BasePresenter<LoginContract.View>(login), LoginContract.Presenter {

    override fun onLogInBtnClick(email: String, pass: String) {
        if (email.isNotEmpty()) {
            if (pass.isNotEmpty()) {
                doLogin(email, pass)
            } else {
                login.showError(login.getMyString(R.string.err_pass_empty))
            }
        } else {
            login.showError(login.getMyString(R.string.err_email_empty))
        }
    }

    private fun doLogin(email: String, pass: String) {
        loginUseCase.execute(
            email,
            pass,
            onSuccess = { checkProfileWhenLogin() },
            onError = { login.showError(it) }
        )
    }

    private fun checkProfileWhenLogin() {
        hasProfileUseCase.execute { hasProfile ->
            if (hasProfile) {
                login.loadMainApplication()
            } else {
                login.loadSignInProfile()
            }
        }
    }

    override fun onSignInBtnClick() {
        login.loadSignIn()
    }

}