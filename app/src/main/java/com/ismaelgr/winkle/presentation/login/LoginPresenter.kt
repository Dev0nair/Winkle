package com.ismaelgr.winkle.presentation.login

import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.domain.usecase.LoginUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class LoginPresenter(
    private val login: LoginContract.View,
    private val loginUseCase: LoginUseCase
) :
    BasePresenter<LoginContract.View>(login), LoginContract.Presenter {

    override fun onLogInBtnClick(email: String, pass: String) {
        if (email.isNotEmpty()) {
            if (pass.isNotEmpty()) {
                loginUseCase.execute(
                    email,
                    pass,
                    onSuccess = { login.loadMainApplication() },
                    onError = { login.showError(it) }
                )
            } else {
                login.showError(login.getMyString(R.string.err_pass_empty))
            }
        } else {
            login.showError(login.getMyString(R.string.err_email_empty))
        }
    }

    override fun onSignInBtnClick() {
        login.loadSignIn()
    }

}