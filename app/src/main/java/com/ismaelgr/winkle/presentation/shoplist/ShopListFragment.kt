package com.ismaelgr.winkle.presentation.shoplist

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.factory.AccountRepositoryFactory
import com.ismaelgr.winkle.data.repository.factory.CestaRepositoryFactory
import com.ismaelgr.winkle.data.repository.factory.ProductsRepositoryFactory
import com.ismaelgr.winkle.data.repository.factory.ProfileRepositoryFactory
import com.ismaelgr.winkle.domain.usecase.GetMyCestaUseCase
import com.ismaelgr.winkle.domain.usecase.GetProductosMiCesta
import com.ismaelgr.winkle.presentation.base.BaseContract
import kotlinx.android.synthetic.main.fragment_shoplist.*

/**
 * A simple [Fragment] subclass.
 */
class ShopListFragment : BaseFragment(R.layout.fragment_shoplist), ShopListContract.View {

    private lateinit var shoplistPresenter: ShopListContract.Presenter
    private lateinit var cestaRecyclerAdapter: CestaRecyclerAdapter

    override fun initElements() {
        shoplistPresenter = ShopListPresenter(
            this as ShopListContract.View,
            GetProductosMiCesta(
                AccountRepositoryFactory().getRepository(),
                ProfileRepositoryFactory().getRepository(),
                CestaRepositoryFactory().getRepository(),
                ProductsRepositoryFactory().getRepository()
            )
        )

        configureRecyclerView()

        shoplistPresenter.onInit()
    }

    private fun configureRecyclerView(): Unit {
        cestaRecyclerAdapter = CestaRecyclerAdapter(shoplistPresenter::onDeleteItemClick, shoplistPresenter::onItemClick)

        shoppinglist_rv.run {
            adapter = cestaRecyclerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun bindPresenter(): BaseContract.Presenter = this.shoplistPresenter

    override fun loadCesta(productos: List<Producto>) {
        cestaRecyclerAdapter.loadList(productos)
    }
}
