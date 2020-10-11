package com.ismaelgr.winkle.presentation.signin2

import com.ismaelgr.winkle.presentation.base.BaseContract

interface SignIn2Contract {

    interface View : BaseContract.View {
        fun navigateBack()
        fun navigateMainApp()
    }
    interface Presenter : BaseContract.Presenter {
        fun onSignInClick(username: String, contactEmail: String, contactPhone: String, description: String)
        fun onBackClick()
    }
}