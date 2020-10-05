package com.ismaelgr.winkle.presentation.myproducts

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class MyProductsFragment : BaseFragment(R.layout.fragment_myproducts), MyProductsContract.View {

    private lateinit var myproductsPresenter: MyProductsContract.Presenter

    override fun initElements() {
        myproductsPresenter = MyProductsPresenter(this as MyProductsContract.View)
    }
}
