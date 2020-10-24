package com.ismaelgr.winkle.util

import com.google.firebase.firestore.*
import io.reactivex.rxjava3.core.Maybe

object FirebaseListener {

    fun <T> makeOneTimeQueryListener(
        query: Query,
        classCast: Class<T>
    ) = Maybe.create<List<T>> { emiter ->
        query.get()
            .addOnSuccessListener { it.toObjects(classCast).run(emiter::onSuccess) }
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
            .addOnSuccessListener { it.toObject(classCast).run(emiter::onSuccess) }
            .addOnFailureListener(emiter::onError)
    }


    fun makeFullTimeListener(
        collectionPath: String,
        onSuccess: (Any) -> Unit,
        onError: (Exception) -> Unit
    ) {
        val firebaseListener = FirebaseFirestore.getInstance().collection(collectionPath)

        firebaseListener.addSnapshotListener { value, error ->
            error?.run(onError)
            value?.run(onSuccess)
        }
    }
}