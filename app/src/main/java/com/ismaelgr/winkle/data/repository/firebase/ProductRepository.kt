package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.util.FirebaseListener
import com.ismaelgr.winkle.util.Routes
import io.reactivex.rxjava3.core.Maybe

class ProductRepository : ProductRepositoryNeed {

    override fun getProductsOf(
        idProfile: String
    ): Maybe<List<Producto>> =
        FirebaseListener.makeOneTimeQueryListener(
            query = FirebaseFirestore.getInstance().collection(Routes.PRODUCTOS)
                .whereEqualTo("vendedorId", idProfile),
            classCast = Producto::class.java
        )


    override fun getAllProducts(): Maybe<List<Producto>> =
        FirebaseListener.makeOneTimeQueryListener(
            query = FirebaseFirestore.getInstance().collection(Routes.PRODUCTOS),
            classCast = Producto::class.java
        )


    override fun getProductInfo(
        idProducto: String
    ): Maybe<Producto> =
        Maybe.create { emitter ->
            FirebaseListener.makeOneTimeQueryListener(
                query = FirebaseFirestore.getInstance().collection(Routes.PRODUCTOS)
                    .whereEqualTo("id", idProducto),
                classCast = Producto::class.java
            )
                .doOnSuccess { emitter.onSuccess(it[0]) }
                .doOnComplete(emitter::onComplete)
                .doOnError(emitter::onError)
        }
}