package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Puntuacion
import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.needs.RateRepositoryNeed
import com.ismaelgr.winkle.util.FirebaseListener
import com.ismaelgr.winkle.util.Routes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class RateRepository : RateRepositoryNeed {

    private var firestore: FirebaseFirestore? = null

    override fun getTotalRate(): Int = Configuration.MAX_RATE

    override fun getRatesOf(idProfile: String): Maybe<List<Puntuacion>> =
        Maybe.create { emitter ->
            FirebaseListener.makeOneTimeQueryListener(
                query = getFirestore().collection(Routes.FAVORITOS)
                    .whereEqualTo("vendedorId", idProfile),
                Puntuacion::class.java
            ).subscribe(
                { list ->
                    emitter.onSuccess(list)
                },
                {
                    emitter.onError(it)
                },
                {
                    emitter.onComplete()
                }
            )
        }

    override fun addRating(puntuacion: Puntuacion): Completable =
        Completable.create { emitter ->
            val newPos = getFirestore().collection(Routes.FAVORITOS).document()
            puntuacion.id = newPos.id

            newPos.set(puntuacion)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener(emitter::onError)
        }

    private fun getFirestore(): FirebaseFirestore {
        if (firestore == null) {
            firestore = FirebaseFirestore.getInstance()
        }

        return firestore!!
    }
}