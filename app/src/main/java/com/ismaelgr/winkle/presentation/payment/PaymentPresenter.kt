package com.ismaelgr.winkle.presentation.payment

import com.ismaelgr.winkle.presentation.base.BasePresenter

class PaymentPresenter(view: PaymentContract.View) :
    BasePresenter<PaymentContract.View>(view), PaymentContract.Presenter {

}