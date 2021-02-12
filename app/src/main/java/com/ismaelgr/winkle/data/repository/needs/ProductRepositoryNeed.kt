package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Producto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface ProductRepositoryNeed {

    fun getProductsOf(idProfile: String): Maybe<List<Producto>>

    fun getAllProducts(): Maybe<List<Producto>>

    fun getAllProductsExcept(idProfile: String): Maybe<List<Producto>>

    fun getProductInfo(idProducto: String): Maybe<Producto>

    fun getProductsInfo(idProductos: List<String>): Maybe<List<Producto>>

    fun saveProduct(vararg producto: Producto): Completable

    fun createProduct(vararg producto: Producto): Completable
}