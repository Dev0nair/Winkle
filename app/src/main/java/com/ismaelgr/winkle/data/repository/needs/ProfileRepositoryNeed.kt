package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Perfil

interface ProfileRepositoryNeed {

    fun hasProfile(idAccount: String, onSuccess: (Boolean) -> Unit, onError: (String) -> Unit)
    fun getProfile(idProfile: String, onSuccess: (Perfil) -> Unit, onError: (String) -> Unit)
    fun createProfile(perfil: Perfil, onSuccess: () -> Unit, onError: (String) -> Unit)
}