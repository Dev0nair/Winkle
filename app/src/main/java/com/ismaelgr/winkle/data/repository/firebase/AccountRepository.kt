package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ismaelgr.winkle.data.entity.Cuenta
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.util.Mapper
import io.reactivex.rxjava3.core.Completable

class AccountRepository : AccountRepositoryNeed {

    private var firebaseAuth: FirebaseAuth? = null

    private var cuenta: Cuenta? = null

    override fun createAccount(
        email: String,
        pass: String
    ): Completable = Completable.create { emitter ->
        getFirebaseInstance().createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener { emitter.onComplete() }
            .addOnFailureListener { it.run(emitter::onError) }
    }

    override fun getAccount(): Cuenta = getCuenta()

    override fun isLogged(): Boolean = getFirebaseInstance().currentUser != null

    override fun login(
        email: String,
        pass: String
    ): Completable = Completable.create { emitter ->
        getFirebaseInstance().signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener { emitter.onComplete() }
            .addOnFailureListener { it.run(emitter::onError) }
    }

    override fun logout() {
        getFirebaseInstance().signOut()
        this.firebaseAuth = null
    }

    private fun getFirebaseInstance(): FirebaseAuth {
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance()
        }

        return firebaseAuth!!
    }

    private fun getCuenta(): Cuenta {
        if(cuenta == null){
            val u: FirebaseUser = getFirebaseInstance().currentUser ?: throw Error("You're not logged in")
            cuenta = Mapper.map(u)
        }

        return cuenta!!
    }
}