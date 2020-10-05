package com.ismaelgr.winkle.presentation.base

abstract class BasePresenter<T : BaseContract.View>(private val view: T) : BaseContract.Presenter