package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Cesta
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface CestaRepositoryNeed {

    fun getCesta(idProfile: String): Maybe<List<Cesta>>

    fun addToCesta(idProfile: String, idProduct: String): Completable

    fun clearCesta(idProfile: String): Completable

    fun deleteFromCesta(idProfile: String, idCesta: String): Completable
}