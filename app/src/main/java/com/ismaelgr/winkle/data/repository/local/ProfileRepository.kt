package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed

class ProfileRepository: ProfileRepositoryNeed {

    private val perfil = Perfil(
        id = "1",
        username = "Ismael González",
        email = "correo_prueba@gmail.com",
        telefono = "654725348",
        image = "",
        descripcion = "Una descripcion de ejemplo",
        emailContacto = "correo2_prueba@gmail.com"
    )

    override fun hasProfile(idAccount: String, onSuccess: (Boolean) -> Unit, onError: (String) -> Unit) {
        onSuccess(true)
    }

    override fun getProfile(
        idProfile: String,
        onSuccess: (Perfil) -> Unit,
        onError: (String) -> Unit
    ) {
        onSuccess(perfil)
    }

    override fun createProfile(perfil: Perfil, onSuccess: () -> Unit, onError: (String) -> Unit) {
        onSuccess()
    }
}