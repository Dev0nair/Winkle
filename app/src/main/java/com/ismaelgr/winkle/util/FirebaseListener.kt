package com.ismaelgr.winkle.util

import com.google.firebase.firestore.*
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

object FirebaseListener {

    fun <T> makeOneTimeQueryListener(
        query: Query,
        classCast: Class<T>
    ) = Maybe.create<List<T>> { emiter ->
        query.get()
            .addOnSuccessListener {
                val data = it.toObjects(classCast)
                if (data.isNotEmpty()){
                    data.run(emiter::onSuccess)
                } else {
                    emiter.onComplete()
                }
            }
            .addOnFailureListener(emiter::onError)
    }

    fun makeOneTimeCollectionListener(
        collectionReference: CollectionReference,
        onSuccess: (QuerySnapshot) -> Unit,
        onCancel: () -> Unit = {},
        onError: (Exception) -> Unit
    ) {
        val firebaseListener = collectionReference.get()

        firebaseListener
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onError)
            .addOnCanceledListener(onCancel)
    }

    fun <T> makeOneTimeDocumentListener(
        documentReference: DocumentReference,
        classCast: Class<T>
    ) = Maybe.create<T> { emiter ->
        documentReference.get()
            .addOnSuccessListener {
                val data = it.toObject(classCast)
                if (data != null) {
                    data.run(emiter::onSuccess)
                } else {
                    emiter.onComplete()
                }
            }
            .addOnFailureListener(emiter::onError)
    }


    fun <T> makeFullTimeCollectionListener(
        collectionReference: CollectionReference,
        classCast: Class<T>
    ): Observable<Pair<T, DocumentChange.Type>> {
        return Observable.create { emitter ->
            collectionReference
                .addSnapshotListener { value, error ->
                    value?.documentChanges?.forEach {
                        val data = it.document.toObject(classCast)
                        val change = it.type
                        emitter.onNext(Pair(data, change))
                    }
                    error?.run(emitter::onError)
                }

        }
    }

    fun <T> makeFullTimeDocumentListener(
        documentReference: DocumentReference,
        classCast: Class<T>
    ): Observable<T> {
        return Observable.create { emitter ->
            documentReference
                .addSnapshotListener { value, error ->
                    value?.toObject(classCast).run(emitter::onNext)
                    error?.run(emitter::onError)
                }

        }
    }
}