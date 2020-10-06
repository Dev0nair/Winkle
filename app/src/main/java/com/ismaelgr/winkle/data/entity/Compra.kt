package com.ismaelgr.winkle.data.entity

data class Compra (
    val id: String,
    val productos: List<Producto>,
    val perfilId: String
)