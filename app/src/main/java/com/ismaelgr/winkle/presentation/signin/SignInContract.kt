package com.ismaelgr.winkle.presentation.signin

import com.ismaelgr.winkle.presentation.base.BaseContract

interface SignInContract {

    interface View : BaseContract.View{
        fun navigateNextSignIn()
        fun navigateBack()
        fun enableButtons()
        fun disableButtons()
    }
    interface Presenter : BaseContract.Presenter {
        fun onBackPressed()
        fun onContinuePressed(email: String, pass: String)
    }
}