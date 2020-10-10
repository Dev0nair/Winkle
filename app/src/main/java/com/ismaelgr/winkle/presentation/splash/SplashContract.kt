package com.ismaelgr.winkle.presentation.splash

import com.ismaelgr.winkle.presentation.base.BaseContract

interface SplashContract {

    interface View : BaseContract.View {
        fun loadLegal()
        fun loadLogin()
        fun loadMainApplication()
        fun loadAnimation(onTimeCompleted: () -> Unit, timeToProceed: Long)
    }

    interface Presenter : BaseContract.Presenter {
        fun onInitElements()
    }
}