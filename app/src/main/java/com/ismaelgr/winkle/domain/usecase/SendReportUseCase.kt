package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.entity.Report
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed
import com.ismaelgr.winkle.data.repository.needs.ReportsRepositoryNeed

class SendReportUseCase(
    private val accountRepositoryNeed: AccountRepositoryNeed,
    private val profileRepositoryNeed: ProfileRepositoryNeed,
    private val reportsRepositoryNeed: ReportsRepositoryNeed
) {

    fun execute(
        reason: String,
        idProduct: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        accountRepositoryNeed.getAccount().let { account ->
            profileRepositoryNeed.getProfileFromAcc(account.id)
                .subscribe(
                    { profile ->
                        val report = Report("", profile.id, idProduct, reason)
                        reportsRepositoryNeed.sendReport(report)
                            .subscribe(onSuccess)
                    },
                    {
                        onError(it.message.toString())
                    }
                )
        }

    }

}