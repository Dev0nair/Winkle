package com.ismaelgr.winkle.presentation.productdetails

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailsFragment : BaseFragment(R.layout.fragment_productdetails), ProductDetailsContract.View {

    private lateinit var productdetailsPresenter: ProductDetailsContract.Presenter

    override fun initElements() {
        productdetailsPresenter = ProductDetailsPresenter(this as ProductDetailsContract.View)
    }
}
