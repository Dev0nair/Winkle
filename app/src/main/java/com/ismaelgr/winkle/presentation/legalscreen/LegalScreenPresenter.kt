package com.ismaelgr.winkle.presentation.legalscreen

import com.ismaelgr.winkle.domain.usecase.LegalConfirmationUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class LegalScreenPresenter(
    private val legal: LegalScreenContract.View,
    private val legalConfirmationUseCase: LegalConfirmationUseCase
) :
    BasePresenter<LegalScreenContract.View>(legal), LegalScreenContract.Presenter {

    override fun onAcceptButtonClick() {
        legalConfirmationUseCase.apply {
            legal.loadLogin()
        }
    }
}