package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed
import com.ismaelgr.winkle.util.FirebaseListener
import com.ismaelgr.winkle.util.Routes

class CestaRepository : CestaRepositoryNeed {

    override fun getCesta(idProfile: String, onLoad: (Cesta) -> Unit, onError: (String) -> Unit) {

        FirebaseListener.makeOneTimeDocumentListener(
            documentReference = FirebaseFirestore.getInstance().collection(Routes.CESTAS)
                .document(idProfile),
            onSuccess = { data -> data.toObject(Cesta::class.java)?.run(onLoad) },
            onError = { error -> error.message.toString().run(onError) }
        )
    }

    override fun addToCesta(
        idProfile: String,
        idProduct: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        FirebaseListener.makeOneTimeDocumentListener(
            documentReference = FirebaseFirestore.getInstance().collection(Routes.CESTAS)
                .document(idProfile),
            onSuccess = { data ->
                var cesta = data.toObject(Cesta::class.java)
                if (cesta == null) {
                    cesta = Cesta(idProfile, arrayListOf())
                }
                cesta.products.add(idProduct)

                FirebaseFirestore.getInstance().collection(Routes.CESTAS).document(idProfile)
                    .set(cesta)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onError(it.message.toString()) }
            },
            onError = { error -> error.message.toString().run(onError) }
        )
    }

    override fun clearCesta(idProfile: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        FirebaseFirestore.getInstance().collection(Routes.CESTAS).document(idProfile)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message.toString()) }
    }

    override fun deleteFromCesta(
        idProfile: String,
        idProduct: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        FirebaseListener.makeOneTimeDocumentListener(
            documentReference = FirebaseFirestore.getInstance().collection(Routes.CESTAS)
                .document(idProfile),
            onSuccess = { data ->
                val cesta = data.toObject(Cesta::class.java)
                if (cesta != null) {
                    if (cesta.products.contains(idProduct)) {
                        cesta.products.remove(idProduct)

                        FirebaseFirestore.getInstance().collection(Routes.CESTAS)
                            .document(idProfile)
                            .set(cesta)
                            .addOnSuccessListener { onSuccess() }
                            .addOnFailureListener { onError(it.message.toString()) }
                    }
                }
            },
            onError = { error -> error.message.toString().run(onError) }
        )
    }
}