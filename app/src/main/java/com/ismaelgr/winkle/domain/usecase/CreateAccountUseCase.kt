package com.ismaelgr.winkle.domain.usecase

import com.google.firebase.auth.FirebaseAuth

class CreateAccountUseCase {

    fun execute(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message.toString()) }
    }
}