package com.ismaelgr.winkle.util

import android.content.Context
import com.google.firebase.auth.FirebaseUser
import com.ismaelgr.winkle.data.entity.Categorias
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

    fun map(context: Context, categorias: Categorias): String =
        when (categorias) {
            Categorias.ESPECIAL -> "Especial"
            Categorias.ACCESORIOS -> "Accesorios"
            Categorias.TECNOLOGIA -> "Tecnologia"
            Categorias.SERVICIO -> "Servicio"
            Categorias.ESTUDIOS -> "Estudios"
            Categorias.JUEGOS -> "Juegos"
            Categorias.ROPA_MUJER -> "Ropa mujer"
            Categorias.ROPA_HOMBRE -> "Ropa hombre"
            Categorias.HOGAR -> "Hogar"
        }

    fun map(context: Context, category: Int): String = map(context, map(category))

    fun map(category: Int): Categorias {
        return when (category) {
            Categorias.ESPECIAL.ordinal -> Categorias.ESPECIAL
            Categorias.ACCESORIOS.ordinal -> Categorias.ACCESORIOS
            Categorias.TECNOLOGIA.ordinal -> Categorias.TECNOLOGIA
            Categorias.SERVICIO.ordinal -> Categorias.SERVICIO
            Categorias.ESTUDIOS.ordinal -> Categorias.ESTUDIOS
            Categorias.JUEGOS.ordinal -> Categorias.JUEGOS
            Categorias.ROPA_MUJER.ordinal -> Categorias.ROPA_MUJER
            Categorias.ROPA_HOMBRE.ordinal -> Categorias.ROPA_HOMBRE
            Categorias.HOGAR.ordinal -> Categorias.HOGAR
            else -> Categorias.ESPECIAL
        }
    }
}