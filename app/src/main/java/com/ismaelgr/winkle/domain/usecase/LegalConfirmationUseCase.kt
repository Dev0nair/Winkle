package com.ismaelgr.winkle.domain.usecase

import android.content.Context.MODE_PRIVATE
import com.google.firebase.FirebaseApp
import com.ismaelgr.winkle.util.Consts

class LegalConfirmationUseCase {

    /*
    * Comprobamos si el usuario ha comprobado los términos y condiciones
    * */
    fun execute(onLoad: (Boolean) -> Unit) {
        val context = FirebaseApp.getInstance().applicationContext
        val sharedPreferences = context.getSharedPreferences("winkleInformation", MODE_PRIVATE)
        val value = sharedPreferences.getBoolean(Consts.SHARED_LEGAL_KEY, false)
        onLoad(value)
    }

    /*
    * Constará como que el usuario ha aceptado los términos y condiciones
    * */
    fun apply(onLoad: () -> Unit) {
        val context = FirebaseApp.getInstance().applicationContext
        val sharedPreferences = context.getSharedPreferences("winkleInformation", MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(Consts.SHARED_LEGAL_KEY, true).apply()
        onLoad()
    }
}