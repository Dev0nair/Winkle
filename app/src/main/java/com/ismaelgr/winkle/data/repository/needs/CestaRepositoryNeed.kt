package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Cesta
import io.reactivex.rxjava3.core.Maybe

interface CestaRepositoryNeed {

    fun getCesta(idProfile: String): Maybe<Cesta>

    fun addToCesta(idProfile: String, idProduct: String): Maybe<Any>

    fun clearCesta(idProfile: String): Maybe<Any>

    fun deleteFromCesta(idProfile: String, idProduct: String): Maybe<Any>
}