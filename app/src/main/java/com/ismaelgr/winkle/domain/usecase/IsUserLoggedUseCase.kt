package com.ismaelgr.winkle.domain.usecase

import com.google.firebase.auth.FirebaseAuth

class IsUserLoggedUseCase {

    fun execute(onLoad: (Boolean) -> Unit) {
        val value = FirebaseAuth.getInstance().currentUser != null
        onLoad(value)
    }
}