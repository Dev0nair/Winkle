package com.ismaelgr.winkle.presentation.base

import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.StringRes

interface BaseContract {

    interface View {
        fun initElements()
        fun showError(error: String)
        fun getMyString(@StringRes stringId: Int): String
        fun showLoading()
        fun hideLoading()
        fun animate(
            vararg views: android.view.View,
            @AnimRes animId: Int,
            onStart: () -> Unit = {},
            onFinish: () -> Unit = {}
        )
        fun showReportToolbar(show: Boolean)
    }

    interface Presenter {
        fun showLoading(show: Boolean)
        fun showError(error: String)
        fun onDestroy()
    }
}