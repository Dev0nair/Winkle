package com.ismaelgr.winkle.presentation.base

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<T : BaseContract.View>(private val view: T) : BaseContract.Presenter{

    override fun showLoading(show: Boolean) {
        view.run {
            if (show) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    override fun showError(error: String) {
        view.showError(error)
    }

    override fun showMsg(msg: String) {
        view.showMsg(msg)
    }

    override fun onDestroy() {
    }
}