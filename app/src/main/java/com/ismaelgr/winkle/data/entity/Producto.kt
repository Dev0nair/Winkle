package com.ismaelgr.winkle.data.entity

data class Producto (
    val id: String,
    val nombre: String,
    val descripcion: String,
    val image: String,
    val precio: Float,
    val vendedorId: String,
    val activo: Boolean,
    val etiquetas: List<String>,
    val categorias: Int
)