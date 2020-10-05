package com.ismaelgr.winkle.presentation.purchases

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class PurchasesFragment : BaseFragment(R.layout.fragment_purchases), PurchasesContract.View {

    private lateinit var purchasesPresenter: PurchasesContract.Presenter

    override fun initElements() {
        purchasesPresenter = PurchasesPresenter(this as PurchasesContract.View)
    }
}
