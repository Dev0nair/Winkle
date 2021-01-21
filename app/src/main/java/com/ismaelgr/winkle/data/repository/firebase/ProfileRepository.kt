package com.ismaelgr.winkle.data.repository.firebase

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import com.ismaelgr.winkle.util.Routes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class ProfileRepository(private val context: Context) : ProfileRepositoryNeed {

    private var firestore: FirebaseFirestore? = null

    override fun hasProfile(
        idAccount: String
    ): Single<Boolean> = Single.create { emitter ->
        getFirestore().collection(Routes.PERFILES)
            .whereEqualTo("id", idAccount)
            .get()
            .addOnSuccessListener { (!it.isEmpty).run(emitter::onSuccess) }
            .addOnFailureListener {
                false.run(emitter::onSuccess)
                it.run(emitter::onError)
            }
    }

    override fun getProfile(
        idProfile: String
    ): Maybe<Perfil> = Maybe.create { emitter ->
        getFirestore().collection(Routes.PERFILES).document(idProfile)
            .get()
            .addOnSuccessListener { it.toObject(Perfil::class.java)?.run(emitter::onSuccess) }
            .addOnFailureListener {
                it.run(emitter::onError)
            }
    }

    override fun getProfileFromAcc(
        idAccount: String
    ): Maybe<Perfil> = Maybe.create { emitter ->
        getFirestore().collection(Routes.PERFILES)
            .whereEqualTo("id", idAccount)
            .get()
            .addOnSuccessListener {
                val perfil = it.toObjects(Perfil::class.java)[0]
                emitter.onSuccess(perfil)
                saveProfile(perfil)
            }
            .addOnFailureListener {
                it.run(emitter::onError)
            }
    }

    private fun saveProfile(perfil: Perfil) {
        context.getSharedPreferences(javaClass.name, Context.MODE_PRIVATE)
            .edit()
            .putString("profileID", perfil.id)
            .apply()
    }

    override fun getSavedProfile(): Maybe<Perfil> = getProfile(
        context.getSharedPreferences(javaClass.name, Context.MODE_PRIVATE)
            .getString("profileID", "no-profile").toString()
    )

    override fun createProfile(perfil: Perfil): Completable =
        Completable.create { emitter ->
            val ref = getFirestore().collection(Routes.PERFILES).document()
            perfil.id = ref.id

            ref.set(perfil)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { it.run(emitter::onError) }
        }

    private fun getFirestore(): FirebaseFirestore {
        if (firestore == null) {
            firestore = FirebaseFirestore.getInstance()
        }

        return firestore!!
    }
}