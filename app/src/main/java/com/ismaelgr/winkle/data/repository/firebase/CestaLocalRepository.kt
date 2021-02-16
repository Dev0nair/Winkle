package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed
import com.ismaelgr.winkle.util.FirebaseListener
import com.ismaelgr.winkle.util.Routes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class CestaLocalRepository : CestaRepositoryNeed {

    private var firestore: FirebaseFirestore? = null

    override fun getCesta(idProfile: String): Maybe<List<Cesta>> =
        FirebaseListener.makeOneTimeQueryListener(
            getFirestore().collection(Routes.CESTAS).whereEqualTo("idProfile", idProfile),
            Cesta::class.java
        )

    override fun addToCesta(
        idProfile: String,
        idProduct: String
    ) = Completable.create { emiter ->
        val cestaRef = getFirestore().collection(Routes.CESTAS).document()
        val cesta = Cesta(cestaRef.id, idProfile, idProduct)
        cestaRef.set(cesta)
            .addOnSuccessListener { emiter.onComplete() }
            .addOnFailureListener(emiter::onError)
            .addOnCompleteListener { emiter.onComplete() }
    }

    override fun clearCesta(idProfile: String) =
        Completable.create { emitter ->
            getFirestore().collection(Routes.CESTAS).document(idProfile)
                .delete()
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { it.run(emitter::onError) }
        }

    override fun deleteFromCesta(
        idProfile: String,
        idCesta: String
    ) = Completable.create { emitter ->
        getFirestore().document(idCesta).delete()
            .addOnSuccessListener { emitter.onComplete() }
            .addOnCompleteListener { emitter.onComplete() }
            .addOnFailureListener(emitter::onError)
    }

    private fun getFirestore(): FirebaseFirestore {
        if (firestore == null) {
            firestore = FirebaseFirestore.getInstance()
        }

        return firestore!!
    }
}