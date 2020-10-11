package com.ismaelgr.winkle.presentation.signin

import com.ismaelgr.winkle.presentation.base.BasePresenter

class SignInPresenter(private val signin: SignInContract.View) :
    BasePresenter<SignInContract.View>(signin), SignInContract.Presenter {

    override fun onBackPressed() {
        signin.navigateBack()
    }

    override fun onContinuePressed(email: String, pass: String) {
        // TODO("AQUI TENEMOS QUE COMPROBAR SI PODEMOS USAR ESE CORREO")
        signin.navigateNextSignIn()
    }

}