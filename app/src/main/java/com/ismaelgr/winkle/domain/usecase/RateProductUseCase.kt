package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Puntuacion
import com.ismaelgr.winkle.data.repository.needs.RateRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class RateProductUseCase(private val rateRepositoryNeed: RateRepositoryNeed) {

    private var disposable: Disposable? = null

    fun execute(puntuacion: Puntuacion, onSuccess: () -> Unit, onError: (String) -> Unit){
        disposable = rateRepositoryNeed.addRating(puntuacion)
            .subscribe(
                onSuccess,
                {onError(it.message.toString())}
            )
    }

    fun dispose() {
        disposable?.dispose()
    }
}