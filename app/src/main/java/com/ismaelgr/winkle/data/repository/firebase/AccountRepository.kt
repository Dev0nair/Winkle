package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.auth.FirebaseAuth
import com.ismaelgr.winkle.data.entity.Cuenta
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.util.Mapper
import io.reactivex.rxjava3.core.Completable

class AccountRepository : AccountRepositoryNeed {

    override fun createAccount(
        email: String,
        pass: String
    ): Completable = Completable.create { emitter ->
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener { emitter.onComplete() }
            .addOnFailureListener { it.run(emitter::onError) }
    }

    override fun getAccount(): Cuenta {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            return Mapper.map(firebaseUser)
        } else {
            throw Error("You are not logged!")
        }
    }

    override fun isLogged(): Boolean = FirebaseAuth.getInstance().currentUser != null

    override fun login(
        email: String,
        pass: String
    ): Completable = Completable.create { emitter ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener { emitter.onComplete() }
            .addOnFailureListener { it.run(emitter::onError) }
    }

    override fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
    }
}