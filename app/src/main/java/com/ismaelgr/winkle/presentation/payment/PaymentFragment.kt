package com.ismaelgr.winkle.presentation.payment

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.presentation.base.BaseContract

/**
 * A simple [Fragment] subclass.
 */
class PaymentFragment : BaseFragment(R.layout.fragment_payment), PaymentContract.View {

    private lateinit var paymentPresenter: PaymentContract.Presenter

    override fun bindPresenter(): BaseContract.Presenter = this.paymentPresenter

    override fun initElements() {
        paymentPresenter = PaymentPresenter(this as PaymentContract.View)
    }
}
