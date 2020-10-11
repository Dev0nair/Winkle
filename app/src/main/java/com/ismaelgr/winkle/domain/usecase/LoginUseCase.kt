package com.ismaelgr.winkle.domain.usecase

import com.google.firebase.auth.FirebaseAuth

class LoginUseCase {

    fun execute(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message.orEmpty()) }
    }
}