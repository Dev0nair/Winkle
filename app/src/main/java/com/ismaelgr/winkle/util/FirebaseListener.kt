package com.ismaelgr.winkle.util

import com.google.firebase.firestore.*
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

object FirebaseListener {

    fun <T> makeOneTimeQueryListener(
        query: Query,
        classCast: Class<T>
    ): Maybe<List<T>> = Maybe.create { emiter ->
        query.get()
            .addOnSuccessListener {
                val data = it.toObjects(classCast)
                if (data.isNotEmpty()) {
                    data.run(emiter::onSuccess)
                } else {
                    emiter.onComplete()
                }
            }
            .addOnCompleteListener { emiter.onComplete() }
            .addOnFailureListener { emiter.onError(it) }
            .addOnCanceledListener { emiter.onError(Error("Canceled")) }
    }

    fun <T> makeOneTimeDocumentListener(
        documentReference: DocumentReference,
        classCast: Class<T>
    ): Maybe<T> = Maybe.create { emitter ->
        documentReference.get()
            .addOnSuccessListener {
                val data = it.toObject(classCast)
                if (data != null) {
                    data.run(emitter::onSuccess)
                } else {
                    emitter.onComplete()
                }
            }
            .addOnFailureListener(emitter::onError)
    }

}