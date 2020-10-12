package com.ismaelgr.winkle.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.util.Routes

class CreateProfileUseCase {

    fun execute(perfil: Perfil, onSuccess: () -> Unit, onError: (String) -> Unit) {
        FirebaseAuth.getInstance().currentUser?.let { user ->
            perfil.id = user.uid
            perfil.email = user.email.toString()

            FirebaseFirestore.getInstance().collection(Routes.PERFILES)
                .document()
                .set(perfil)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { onError(it.message.toString()) }
        }
    }
}