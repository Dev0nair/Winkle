package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ReportsRepositoryNeed
import io.reactivex.rxjava3.disposables.Disposable

class HasReportedProductUseCase(
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed,
    private val reportsRepositoryNeed: ReportsRepositoryNeed
) {
    private var disposable: Disposable? = null

    fun execute(
        idProduct: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        accountRepositoryNeed.getAccount().let { account ->
            disposable = profileRepositoryNeed.getProfileFromAcc(account.id)
                .doOnError { onError(it.message.toString()) }
                .subscribe { profile ->
                    reportsRepositoryNeed.getReportsOf(profile.id)
                        .subscribe { reports ->
                            onSuccess(
                                reports.any { it.productoId == idProduct }
                            )
                        }
                }
        }
    }

    fun dispose() {
        disposable?.dispose()
    }
}