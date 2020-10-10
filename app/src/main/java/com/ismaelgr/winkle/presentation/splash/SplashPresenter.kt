package com.ismaelgr.winkle.presentation.splash

import com.ismaelgr.winkle.domain.usecase.LegalConfirmationUseCase
import com.ismaelgr.winkle.domain.usecase.IsUserLoggedUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter
import com.ismaelgr.winkle.util.Consts

class SplashPresenter(
    private val splash: SplashContract.View,
    private val legalConfirmationUseCase: LegalConfirmationUseCase,
    private val isUserLoggedUseCase: IsUserLoggedUseCase
) :
    BasePresenter<SplashContract.View>(splash), SplashContract.Presenter {

    override fun onInitElements() {
        splash.loadAnimation(
            onTimeCompleted = {
                legalConfirmationUseCase.execute { isLegalConfirmed ->
                    if (isLegalConfirmed) {
                        isUserLoggedUseCase.execute { isLogged ->
                            if (isLogged) {
                                splash.loadMainApplication()
                            } else {
                                splash.loadLogin()
                            }
                        }
                    } else {
                        splash.loadLegal()
                    }

                }
            },
            timeToProceed = Consts.SPLASH_DURATION
        )
    }

}