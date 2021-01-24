package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Puntuacion
import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.needs.RateRepositoryNeed
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class RateRepository: RateRepositoryNeed {

    private val rates = arrayListOf(
        Puntuacion("1", "1", "1", "1",5f, "Está guay y todo correcto"),
        Puntuacion("2", "1", "1", "1",2f, "Está guay y todo correcto"),
        Puntuacion("3", "2", "2", "1",4f, "Está guay y todo correcto"),
        Puntuacion("4", "1", "2", "1",3f, "Está guay y todo correcto"),
        Puntuacion("5", "1", "4", "1",5f, "Está guay y todo correcto"),
        Puntuacion("6", "2", "4", "1",1f, "Está guay y todo correcto"),
        Puntuacion("7", "1", "5", "1",4f, "Está guay y todo correcto"),
    )

    override fun getTotalRate() = Configuration.MAX_RATE

    override fun getRatesOf(idProfile: String): Maybe<List<Puntuacion>> =
        Maybe.just(rates.filter { it.vendedorId == idProfile })

    override fun addRating(puntuacion: Puntuacion): Completable =
        Completable.fromAction { rates.add(puntuacion) }
}