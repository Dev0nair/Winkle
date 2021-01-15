package com.ismaelgr.winkle.data.entity

data class Report (
    var id: String,
    val reportadorId: String,
    val productoId: String,
    val motivo: String
)