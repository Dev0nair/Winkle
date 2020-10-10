package com.ismaelgr.winkle.presentation.splash

import android.util.Log
import com.ismaelgr.winkle.presentation.base.BasePresenter
import com.ismaelgr.winkle.util.Consts

class SplashPresenter(private val splash: SplashContract.View) :
    BasePresenter<SplashContract.View>(splash), SplashContract.Presenter {

    override fun onInitElements() {
        splash.loadAnimation(
            onTimeCompleted = {
                /**
                 * Aqui tenemos que comprobar si hemos aceptado las condiciones legales o no.
                 *      Si no lo habíamos aceptado, cargamos Legal.
                 *      Si sí lo habíamos aceptado, comprobamos si estamos logeados
                 *          Si no estabamos logeados, cargamos Login
                 *          Si sí estabamos logeados, cargamos Main (donde está el jugo de la app, que empieza por el home)
                 */

                splash.loadMainApplication()
                Log.i("Splash Fragment", "Se va a cargar main fragment")
            },
            timeToProceed = Consts.SPLASH_DURATION
        )
    }

}