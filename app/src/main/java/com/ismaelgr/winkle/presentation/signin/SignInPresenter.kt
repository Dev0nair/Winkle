package com.ismaelgr.winkle.presentation.signin

import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.domain.usecase.CreateAccountUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class SignInPresenter(
    private val signin: SignInContract.View,
    private val createAccountUseCase: CreateAccountUseCase
) :
    BasePresenter<SignInContract.View>(signin), SignInContract.Presenter {

    override fun onBackPressed() {
        signin.navigateBack()
    }

    override fun onContinuePressed(email: String, pass: String) {
        if (email.isNotEmpty()) {
            if (pass.isNotEmpty()) {
                createAccountUseCase.execute(email, pass,
                    onSuccess = {
                        signin.navigateNextSignIn()
                    }, onError = {
                        signin.showError(it)
                    })
            } else {
                signin.showError(signin.getMyString(R.string.err_pass_empty))
            }
        } else {
            signin.showError(signin.getMyString(R.string.err_email_empty))
        }
    }
}