package com.ismaelgr.winkle.presentation.salessummary

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class SalesSummaryFragment : BaseFragment(R.layout.fragment_salessummary), SalesSummaryContract.View {

    private lateinit var salessummaryPresenter: SalesSummaryContract.Presenter

    override fun initElements() {
        salessummaryPresenter = SalesSummaryPresenter(this as SalesSummaryContract.View)
    }
}
