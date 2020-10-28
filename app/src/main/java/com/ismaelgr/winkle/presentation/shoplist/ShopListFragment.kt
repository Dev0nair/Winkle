package com.ismaelgr.winkle.presentation.shoplist

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.presentation.base.BaseContract

/**
 * A simple [Fragment] subclass.
 */
class ShopListFragment : BaseFragment(R.layout.fragment_shoplist), ShopListContract.View {

    private lateinit var shoplistPresenter: ShopListContract.Presenter


    override fun initElements() {
        shoplistPresenter = ShopListPresenter(this as ShopListContract.View)
    }

    fun configureRecyclerView(): Unit {

    }

    override fun bindPresenter(): BaseContract.Presenter = this.shoplistPresenter

    override fun loadCesta(cesta: Cesta) {
        TODO("Not yet implemented")
    }
}
