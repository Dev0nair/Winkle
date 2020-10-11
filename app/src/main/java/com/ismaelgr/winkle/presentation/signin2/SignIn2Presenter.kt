package com.ismaelgr.winkle.presentation.signin2

import com.ismaelgr.winkle.presentation.base.BasePresenter

class SignIn2Presenter(private val signin2: SignIn2Contract.View) :
    BasePresenter<SignIn2Contract.View>(signin2), SignIn2Contract.Presenter {

    override fun onSignInClick(
        username: String,
        contactEmail: String,
        contactPhone: String,
        description: String
    ) {
        signin2.navigateMainApp()
    }

    override fun onBackClick() {
        signin2.navigateBack()
    }
}