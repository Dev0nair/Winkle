package com.ismaelgr.winkle.util

import com.google.firebase.firestore.*

object FirebaseListener {

    fun makeOneTimeQueryListener(
        query: Query,
        onSuccess: (QuerySnapshot) -> Unit,
        onCancel: () -> Unit = {},
        onError: (Exception) -> Unit
    ) {
        val firebaseListener = query.get()

        firebaseListener
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onError)
            .addOnCanceledListener(onCancel)
    }

    fun makeOneTimeCollectionListener(
        collectionReference: CollectionReference,
        onSuccess: (Any) -> Unit,
        onCancel: () -> Unit = {},
        onError: (Exception) -> Unit
    ) {
        val firebaseListener = collectionReference.get()

        firebaseListener
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onError)
            .addOnCanceledListener(onCancel)
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