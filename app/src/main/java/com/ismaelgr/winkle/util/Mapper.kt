package com.ismaelgr.winkle.util

import com.google.firebase.auth.FirebaseUser
import com.ismaelgr.winkle.data.entity.Cuenta

object Mapper {

    fun map(firebaseUser: FirebaseUser) =
        Cuenta(
            id = firebaseUser.uid,
            username = firebaseUser.displayName.orEmpty(),
            email = firebaseUser.email.orEmpty(),
            phone = firebaseUser.phoneNumber.orEmpty(),
            image = firebaseUser.photoUrl.toString()
        )
}