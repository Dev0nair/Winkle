package com.ismaelgr.winkle.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.util.Routes

class HasProfileUseCase {

    fun execute(onLoad: (Boolean) -> Unit) {
        FirebaseAuth.getInstance().currentUser?.let { user ->
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection(Routes.PERFILES)
                .whereEqualTo("id", user.uid)
                .get()
                .addOnSuccessListener { onLoad(!it.isEmpty) }
                .addOnFailureListener { onLoad(false) }
        }
    }
}