package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Perfil
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface ProfileRepositoryNeed {

    fun hasProfile(idAccount: String): Single<Boolean>
    fun getProfile(idProfile: String): Maybe<Perfil>
    fun getProfileFromAcc(idAccount: String): Single<Perfil>
    fun saveProfile(perfil: Perfil): Completable
    fun getSavedProfile(): Maybe<Perfil>
    fun createProfile(perfil: Perfil): Completable
}