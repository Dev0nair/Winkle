package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed
import com.ismaelgr.winkle.util.FirebaseListener
import com.ismaelgr.winkle.util.Routes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class CestaRepository : CestaRepositoryNeed {

    override fun getCesta(idProfile: String): Maybe<Cesta> =
        FirebaseListener.makeOneTimeDocumentListener(
            FirebaseFirestore.getInstance().collection(Routes.CESTAS).document(idProfile),
            Cesta::class.java
        )

    override fun addToCesta(
        idProfile: String,
        idProduct: String
    ) = Completable.create { emiter ->
        FirebaseListener.makeOneTimeDocumentListener(
            documentReference = FirebaseFirestore.getInstance().collection(Routes.CESTAS)
                .document(idProfile),
            Cesta::class.java
        )
            .doOnSuccess { cesta ->
                cesta.products.add(idProduct)
                applyCesta(idProfile, cesta, emiter::onComplete, emiter::onError)
            }
            .doOnComplete {
                val cesta = Cesta(idProfile, arrayListOf())
                cesta.products.add(idProduct)
                applyCesta(idProfile, cesta, emiter::onComplete, emiter::onError)
            }
    }

    private fun applyCesta(
        idProfile: String,
        cesta: Cesta,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        FirebaseFirestore.getInstance().collection(Routes.CESTAS).document(idProfile)
            .set(cesta)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it) }
    }

    override fun clearCesta(idProfile: String) =
        Completable.create { emitter ->
            FirebaseFirestore.getInstance().collection(Routes.CESTAS).document(idProfile)
                .delete()
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { it.run(emitter::onError) }
        }

    override fun deleteFromCesta(
        idProfile: String,
        idProduct: String
    ) = Completable.create { emitter ->
        FirebaseListener.makeOneTimeDocumentListener(
            documentReference = FirebaseFirestore.getInstance().collection(Routes.CESTAS)
                .document(idProfile),
            Cesta::class.java
        )
            .doOnSuccess { cesta ->
                if (cesta.products.contains(idProduct)) {
                    cesta.products.remove(idProduct)
                    applyCesta(idProfile, cesta, emitter::onComplete, emitter::onError)
                } else {
                    emitter.onComplete()
                }
            }
            .doOnComplete {
                applyCesta(
                    idProfile,
                    Cesta(idProfile, arrayListOf()),
                    { emitter.onComplete() },
                    emitter::onError
                )
            }
    }
}