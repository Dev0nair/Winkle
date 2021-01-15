package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class CestaRepository: CestaRepositoryNeed {

    val cesta: ArrayList<Cesta> = arrayListOf(
        Cesta("1", "1", "1"),
        Cesta("1", "1", "2"),
        Cesta("1", "1", "2"),
        Cesta("1", "1", "3"),
        Cesta("1", "1", "4"),
    )

    override fun getCesta(idProfile: String): Maybe<List<Cesta>> =
        Maybe.just(cesta.filter { it.idProfile == idProfile })

    override fun addToCesta(idProfile: String, idProduct: String): Completable {
        cesta.add(Cesta("1", idProfile, idProduct))
        return Completable.complete()
    }

    override fun clearCesta(idProfile: String): Completable {
        cesta.clear()
        return Completable.complete()
    }

    override fun deleteFromCesta(
        idProfile: String,
        idCesta: String
    ): Completable = Completable.create { emitter ->

        if(cesta.removeAll { it.id == idCesta && idProfile == idProfile }){
            emitter.onComplete()
        } else {
            emitter.onError(Error("No se pudo borrar un producto que no existe"))
        }
    }
}