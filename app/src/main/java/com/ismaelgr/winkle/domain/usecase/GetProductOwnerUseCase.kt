package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class GetProductOwnerUseCase(
    private val profileRepositoryNeed: ProfileRepositoryNeed
) {

    private var disposable: Disposable? = null

    fun execute(idProfile: String, onLoad: (Perfil) -> Unit, onError: (String) -> Unit) {
        disposable = profileRepositoryNeed.getProfile(idProfile)
            .subscribe(
                onLoad,
                {
                    onError(it.message.toString())
                })
    }

    fun dispose() {
        disposable?.dispose()
    }
}