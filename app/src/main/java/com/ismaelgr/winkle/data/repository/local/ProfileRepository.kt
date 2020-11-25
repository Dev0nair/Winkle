package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.CompletableFuture

class ProfileRepository : ProfileRepositoryNeed {

    private val perfil = Perfil(
        id = "1",
        idAccount = "1",
        username = "Ismael González",
        email = "correo_prueba@gmail.com",
        telefono = "654725348",
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeik6d5EHLTi89m_CKLXyShylk4L92YflpJQ&usqp=CAU",
        descripcion = "Una descripcion de ejemplo",
        emailContacto = "correo2_prueba@gmail.com"
    )

    private var withProfile = true

    override fun hasProfile(idAccount: String): Single<Boolean> =
        Single.just(withProfile)

    override fun getProfile(
        idProfile: String
    ): Maybe<Perfil> = Maybe.just(perfil)

    override fun getProfileFromAcc(
        idAccount: String
    ): Maybe<Perfil> = Maybe.just(perfil)

    override fun createProfile(perfil: Perfil): Completable =
        Completable.complete()
}