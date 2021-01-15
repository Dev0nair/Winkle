package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Puntuacion
import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.needs.RateRepositoryNeed
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class RateRepository: RateRepositoryNeed {

    private val rates = arrayListOf(
        Puntuacion("1", "1", "1", 5),
        Puntuacion("2", "1", "1", 2),
        Puntuacion("3", "2", "2", 4),
        Puntuacion("4", "1", "2", 3),
        Puntuacion("5", "1", "4", 5),
        Puntuacion("6", "2", "4", 1),
        Puntuacion("7", "1", "5", 4),
    )

    override fun getTotalRate() = Configuration.MAX_RATE

    override fun getRatesOf(idProduct: String): Maybe<List<Puntuacion>> =
        Maybe.just(rates.filter { it.productoId == idProduct })

    override fun rate(puntuacion: Puntuacion): Completable =
        Completable.fromAction { rates.add(puntuacion) }
}