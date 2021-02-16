package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class AddProductToCestaUseCase(
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed,
    private val cestaRepositoryNeed: CestaRepositoryNeed
) {

    private var disposable: Disposable? = null

    fun execute(idProduct: String, onSuccess: () -> Unit, onError: (String) -> Unit){
        val acc = accountRepositoryNeed.getAccount()
        profileRepositoryNeed.getProfileFromAcc(acc.id)
            .subscribe(
                { myProfile ->
                    disposable = cestaRepositoryNeed.addToCesta(myProfile.id, idProduct)
                        .subscribe(onSuccess, {onError(it.message.toString())})
                },
                {
                    onError(it.message.toString())
                }
            )
    }

    fun dispose() {
        disposable?.dispose()
    }
}