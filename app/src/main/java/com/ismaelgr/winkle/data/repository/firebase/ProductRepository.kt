package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.util.FirebaseListener
import com.ismaelgr.winkle.util.Routes

class ProductRepository: ProductRepositoryNeed {

    override fun getProductsOf(
        idProfile: String,
        onSuccess: (List<Producto>) -> Unit,
        onError: (String) -> Unit
    ) {
        FirebaseListener.makeOneTimeQueryListener(
            query = FirebaseFirestore.getInstance().collection(Routes.PRODUCTOS).whereEqualTo("vendedorId", idProfile),
            onSuccess = { data -> data.toObjects(Producto::class.java).run(onSuccess) },
            onError = { error -> error.message.toString().run(onError) }
        )
    }

    override fun getAllProducts(onSuccess: (List<Producto>) -> Unit, onError: (String) -> Unit) {
        FirebaseListener.makeOneTimeQueryListener(
            query = FirebaseFirestore.getInstance().collection(Routes.PRODUCTOS),
            onSuccess = { data -> data.toObjects(Producto::class.java).run(onSuccess) },
            onError = { error -> error.message.toString().run(onError) }
        )
    }

    override fun getProductInfo(
        idProducto: String,
        onSuccess: (Producto) -> Unit,
        onError: (String) -> Unit
    ) {
        FirebaseListener.makeOneTimeQueryListener(
            query = FirebaseFirestore.getInstance().collection(Routes.PRODUCTOS).whereEqualTo("id", idProducto),
            onSuccess = { data -> data.toObjects(Producto::class.java)[0].run(onSuccess) },
            onError = { error -> error.message.toString().run(onError) }
        )
    }
}