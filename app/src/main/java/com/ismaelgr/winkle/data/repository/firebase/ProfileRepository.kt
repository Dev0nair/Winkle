package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import com.ismaelgr.winkle.util.Routes

class ProfileRepository : ProfileRepositoryNeed {

    override fun hasProfile(
        idAccount: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection(Routes.PERFILES)
            .whereEqualTo("id", idAccount)
            .get()
            .addOnSuccessListener { (!it.isEmpty).run(onSuccess) }
            .addOnFailureListener {
                false.run(onSuccess)
                it.message.toString().run(onError)
            }
    }

    override fun getProfile(
        idProfile: String,
        onSuccess: (Perfil) -> Unit,
        onError: (String) -> Unit
    ) {

    }

    override fun createProfile(perfil: Perfil, onSuccess: () -> Unit, onError: (String) -> Unit) {
        FirebaseFirestore.getInstance().collection(Routes.PERFILES)
            .document()
            .set(perfil)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message.toString()) }
    }
}