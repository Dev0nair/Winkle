package com.ismaelgr.winkle.data.entity

data class Puntuacion (
    var id: String = "",
    val perfilQuePuntua: String,
    val productoId: String,
    val vendedorId: String,
    val puntuacion: Float,
    val comentario: String
)