package com.ismaelgr.winkle.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.google.firebase.auth.FirebaseUser
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Cuenta

object Mapper {

    fun getImagePath(uri: Uri, contentResolver: ContentResolver): String {
        var path = ""
        var dataToCollect = arrayOf(MediaStore.Images.Media.DATA)
        contentResolver.query(uri, dataToCollect, null, null, null)
            ?.let { cursor ->
                if (cursor.moveToFirst()) {
                   path = cursor.getString(cursor.getColumnIndex(dataToCollect[0]))
                }
                cursor.close()
            }

        return path
    }

    fun map(firebaseUser: FirebaseUser) =
        Cuenta(
            id = firebaseUser.uid,
            username = firebaseUser.displayName.orEmpty(),
            email = firebaseUser.email.orEmpty(),
            phone = firebaseUser.phoneNumber.orEmpty(),
            image = firebaseUser.photoUrl.toString()
        )

    fun map(price: Float) =
        price.toString()
            .replace(".0", "")
            .replace('.', ',')

    fun map(context: Context, categorias: Categorias): String =
        when (categorias) {
            Categorias.ESPECIAL -> context.getString(R.string.text_especial)
            Categorias.ACCESORIOS -> context.getString(R.string.text_accesorios)
            Categorias.TECNOLOGIA -> context.getString(R.string.text_tecnologia)
            Categorias.SERVICIO -> context.getString(R.string.text_servicio)
            Categorias.ESTUDIOS -> context.getString(R.string.text_estudios)
            Categorias.JUEGOS -> context.getString(R.string.text_juegos)
            Categorias.ROPA_MUJER -> context.getString(R.string.text_ropamujer)
            Categorias.ROPA_HOMBRE -> context.getString(R.string.text_ropahombre)
            Categorias.HOGAR -> context.getString(R.string.text_hogar)
            Categorias.OTROS -> context.getString(R.string.text_otros)
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
            Categorias.OTROS.ordinal -> Categorias.OTROS
            else -> Categorias.ESPECIAL
        }
    }
}