package com.ismaelgr.winkle.presentation.login

import com.ismaelgr.winkle.presentation.base.BaseContract

interface LoginContract {

    interface View : BaseContract.View {
        fun loadMainApplication()
    }

    interface Presenter : BaseContract.Presenter {
        fun onLoginBtnClick(email: String, pass: String)
    }
}