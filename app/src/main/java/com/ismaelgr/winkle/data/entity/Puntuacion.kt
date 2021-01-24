package com.ismaelgr.winkle.data.entity

data class Puntuacion (
    var id: String = "",
    val perfilId: String,
    val productoId: String,
    val puntuacion: Float
)