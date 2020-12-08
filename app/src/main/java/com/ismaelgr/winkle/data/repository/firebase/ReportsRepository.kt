package com.ismaelgr.winkle.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.ismaelgr.winkle.data.entity.Report
import com.ismaelgr.winkle.data.repository.needs.ReportsRepositoryNeed
import com.ismaelgr.winkle.util.FirebaseListener
import com.ismaelgr.winkle.util.Routes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class ReportsRepository: ReportsRepositoryNeed {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    override fun getAllReports(): Maybe<List<Report>> =
        FirebaseListener.makeOneTimeQueryListener(
            firestore.collection(Routes.REPORTS),
            Report::class.java
        )

    override fun getReportsOf(idPerfil: String): Maybe<List<Report>> =
        FirebaseListener.makeOneTimeQueryListener(
            firestore.collection(Routes.REPORTS).whereEqualTo("reportadorId", idPerfil),
            Report::class.java
        )

    override fun sendReport(report: Report): Completable =
        Completable.create { emitter ->
            firestore.collection(Routes.REPORTS)
                .document()
                .apply {
                    report.id = this.id
                }
                .set(report)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener(emitter::onError)
        }

    override fun getReport(idReport: String): Maybe<Report> =
        FirebaseListener.makeOneTimeDocumentListener(
            firestore.collection(Routes.REPORTS).document(idReport),
            Report::class.java
        )
}