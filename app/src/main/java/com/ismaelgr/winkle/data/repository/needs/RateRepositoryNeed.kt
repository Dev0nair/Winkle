package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Puntuacion
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface RateRepositoryNeed {
    fun getTotalRate(): Int
    fun getRatesOf(idProduct: String): Maybe<List<Puntuacion>>
    fun rate(puntuacion: Puntuacion): Completable
}