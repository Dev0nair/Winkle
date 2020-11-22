package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.kotlin.subscribeBy

class GetProductOwnerUseCase(
    private val profileRepositoryNeed: ProfileRepositoryNeed
) {

    fun getInfoProfile(idProfile: String, onLoad: (Perfil) -> Unit, onError: (String) -> Unit){
        profileRepositoryNeed.getProfile(idProfile)
            .subscribeBy(
                onError = { onError(it.message.toString())},
                onSuccess = onLoad,
            )
    }
}