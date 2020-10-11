package com.ismaelgr.winkle.presentation.signin

import com.ismaelgr.winkle.presentation.base.BaseContract

interface SignInContract {

    interface View : BaseContract.View{
        fun navigateNextSignIn()
        fun navigateBack()
    }
    interface Presenter : BaseContract.Presenter {
        fun onBackPressed()
        fun onContinuePressed(email: String, pass: String)
    }
}