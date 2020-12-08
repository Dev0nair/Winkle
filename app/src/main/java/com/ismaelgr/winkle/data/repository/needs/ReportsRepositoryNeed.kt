package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Report
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface ReportsRepositoryNeed {

    fun getAllReports(): Maybe<List<Report>>
    fun getReportsOf(idPerfil: String): Maybe<List<Report>>
    fun sendReport(report: Report): Completable
    fun getReport(idReport: String): Maybe<Report>
}