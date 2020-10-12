package com.ismaelgr.winkle.presentation.login

import com.ismaelgr.winkle.presentation.base.BaseContract

interface LoginContract {

    interface View : BaseContract.View {
        fun loadMainApplication()
        fun loadSignIn()
        fun loadSignInProfile()
        fun disableButtons()
        fun enableButtons()
    }

    interface Presenter : BaseContract.Presenter {
        fun onLogInBtnClick(email: String, pass: String)
        fun onSignInBtnClick()
    }
}