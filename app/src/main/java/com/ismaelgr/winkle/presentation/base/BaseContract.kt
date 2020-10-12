package com.ismaelgr.winkle.presentation.base

import androidx.annotation.StringRes

interface BaseContract {

    interface View {
        fun initElements()
        fun showError(error: String)
        fun getMyString(@StringRes stringId: Int): String
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun showLoading(show: Boolean)
        fun showError(error: String)
    }
}