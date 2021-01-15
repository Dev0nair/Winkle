package com.ismaelgr.winkle.presentation.legalscreen

import com.ismaelgr.winkle.presentation.base.BaseContract

interface LegalScreenContract {

    interface View : BaseContract.View {
        fun loadLogin() // se da por hecho que no podrá haber usuario si no se aceptó
    }

    interface Presenter : BaseContract.Presenter {
        fun onAcceptButtonClick()
    }
}