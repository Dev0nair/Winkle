package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.RateRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Math.round
import kotlin.math.roundToInt

class GetRateUseCase(private val rateRepositoryNeed: RateRepositoryNeed) {

    private var disposable: Disposable? = null

    fun execute(idProfile: String, onSuccess: (Float) -> Unit, onError: (String) -> Unit) {
        rateRepositoryNeed.getRatesOf(idProfile)
            .subscribe(
                { list ->
                    onSuccess(((list.sumOf { it.puntuacion.toDouble() }) / list.size).roundToInt().toFloat())
                },
                { onError(it.message.toString()) },
                { onSuccess(0f) })
    }

    fun dispose(){
        disposable?.dispose()
    }
}