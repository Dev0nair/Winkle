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
        login.disableButtons()
        showLoading(true)
        loginUseCase.execute(
            email,
            pass,
            onSuccess = {
                checkProfileWhenLogin()
            },
            onError = {
                showLoading(false)
                login.run {
                    showError(it)
                    enableButtons()
                }
            }
        )
    }

    private fun checkProfileWhenLogin() {
        hasProfileUseCase.execute { hasProfile ->
            showLoading(false)
            if (hasProfile) {
                login.loadMainApplication()
            } else {
                login.run {
                    enableButtons()
                    loadSignInProfile()
                }
            }
        }
    }

    override fun onSignInBtnClick() {
        login.loadSignIn()
    }
}