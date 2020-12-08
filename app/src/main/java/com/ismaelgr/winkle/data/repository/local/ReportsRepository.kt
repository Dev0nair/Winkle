package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Report
import com.ismaelgr.winkle.data.repository.needs.ReportsRepositoryNeed
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class ReportsRepository: ReportsRepositoryNeed {

    private val reports = arrayListOf(
        Report("1", "1", "1", "Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo 1"),
        Report("1", "1", "2", "Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo 2"),
        Report("1", "2", "2", "Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo 3"),
        Report("1", "1", "3", "Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo 4"),
        Report("1", "1", "4", "Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo 5"),
        Report("1", "1", "5", "Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo Ofensivo 6"),
    )

    override fun getAllReports(): Maybe<List<Report>> =
        Maybe.just(this.reports)

    override fun getReportsOf(idPerfil: String): Maybe<List<Report>> =
        Maybe.just(this.reports.filter { report -> report.reportadorId == idPerfil })

    override fun sendReport(report: Report): Completable =
        Completable.fromAction { this.reports.add(report) }

    override fun getReport(idReport: String): Maybe<Report> =
        Maybe.just(this.reports.find { it.id == idReport })
}