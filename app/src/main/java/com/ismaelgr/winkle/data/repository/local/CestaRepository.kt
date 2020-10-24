package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed
import io.reactivex.rxjava3.core.Maybe

class CestaRepository: CestaRepositoryNeed {

    val cesta = Cesta(
        id = "1",
        products = arrayListOf("1", "2", "3")
    )

    override fun getCesta(idProfile: String): Maybe<Cesta> =
        Maybe.just(cesta)

    override fun addToCesta(idProfile: String, idProduct: String): Maybe<Any> {
        cesta.products.add(idProduct)
        return Maybe.just(Any())
    }

    override fun clearCesta(idProfile: String): Maybe<Any> {
        cesta.products.clear()
        return Maybe.just(Any())
    }

    override fun deleteFromCesta(
        idProfile: String,
        idProduct: String
    ): Maybe<Any> = Maybe.create { emitter ->
        if(cesta.products.contains(idProduct)) {
            cesta.products.remove(idProduct)
            emitter.onSuccess(Any())
        } else {
            emitter.onError(Error("No se pudo borrar un producto que no existe"))
        }
    }
}