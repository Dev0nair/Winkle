package com.ismaelgr.winkle.presentation.newproduct

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.presentation.base.BaseContract

/**
 * A simple [Fragment] subclass.
 */
class NewProductFragment : BaseFragment(R.layout.fragment_newproduct), NewProductContract.View {

    private lateinit var newproductPresenter: NewProductContract.Presenter

    override fun initElements() {
        newproductPresenter = NewProductPresenter(this as NewProductContract.View)
    }

    override fun bindPresenter(): BaseContract.Presenter = this.newproductPresenter
}
