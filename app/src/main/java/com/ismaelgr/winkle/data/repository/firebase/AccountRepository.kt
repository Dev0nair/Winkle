package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.auth.FirebaseAuth
import com.ismaelgr.winkle.data.entity.Cuenta
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.util.Mapper

class AccountRepository : AccountRepositoryNeed {
    override fun createAccount(
        email: String,
        pass: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message.toString()) }
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
        pass: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message.orEmpty()) }
    }

    override fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
    }
}