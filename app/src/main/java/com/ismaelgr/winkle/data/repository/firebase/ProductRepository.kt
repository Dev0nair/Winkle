package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.util.FirebaseListener
import com.ismaelgr.winkle.util.Routes
import io.reactivex.rxjava3.core.Maybe

class ProductRepository : ProductRepositoryNeed {

    private var firestore: FirebaseFirestore? = null

    override fun getProductsOf(
        idProfile: String
    ): Maybe<List<Producto>> =
        FirebaseListener.makeOneTimeQueryListener(
            query = getFirestore().collection(Routes.PRODUCTOS)
                .whereEqualTo("vendedorId", idProfile),
            classCast = Producto::class.java
        )


    override fun getAllProducts(): Maybe<List<Producto>> =
        FirebaseListener.makeOneTimeQueryListener(
            query = getFirestore().collection(Routes.PRODUCTOS),
            classCast = Producto::class.java
        )

    override fun getAllProductsExcept(idProfile: String): Maybe<List<Producto>> =
        FirebaseListener.makeOneTimeQueryListener(
            query = getFirestore().collection(Routes.PRODUCTOS).whereNotEqualTo("vendedorId", idProfile),
            classCast = Producto::class.java
        )


    override fun getProductInfo(
        idProducto: String
    ): Maybe<Producto> =
        Maybe.create { emitter ->
            FirebaseListener.makeOneTimeQueryListener(
                query = getFirestore().collection(Routes.PRODUCTOS)
                    .whereEqualTo("id", idProducto),
                classCast = Producto::class.java
            )
                .doOnSuccess { emitter.onSuccess(it[0]) }
                .doOnComplete(emitter::onComplete)
                .doOnError(emitter::onError)
        }

    override fun getProductsInfo(idProductos: List<String>): Maybe<List<Producto>> {
        return if(idProductos.isNotEmpty()){
            FirebaseListener.makeOneTimeQueryListener(
                query = getFirestore().collection(Routes.PRODUCTOS)
                    .whereIn("id", idProductos),
                classCast = Producto::class.java
            )
        } else {
            Maybe.just(emptyList())
        }
    }

    private fun getFirestore(): FirebaseFirestore {
        if(firestore == null){
            firestore = FirebaseFirestore.getInstance()
        }

        return firestore!!
    }
}