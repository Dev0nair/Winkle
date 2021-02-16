package com.ismaelgr.winkle.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Producto (
    var id: String,
    var nombre: String,
    var descripcion: String,
    var mainImage: String,
    var images: ArrayList<String>,
    var precio: Float,
    var vendedorId: String,
    var activo: Boolean,
    var etiquetas: List<String>,
    var categoria: Int,
    var disableNextBuy: Boolean = false
): Parcelable {
    constructor() : this("","","","", arrayListOf(),0f, "", false, emptyList(), -1) {

    }
}