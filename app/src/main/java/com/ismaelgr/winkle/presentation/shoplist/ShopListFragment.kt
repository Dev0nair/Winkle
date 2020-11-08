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
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.fragment_shoplist.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class ShopListFragment : BaseFragment(R.layout.fragment_shoplist), ShopListContract.View {

    private val shoplistPresenter: ShopListContract.Presenter by inject<ShopListPresenter> {
        parametersOf(
            this
        )
    }
    private lateinit var cestaRecyclerAdapter: CestaRecyclerAdapter

    override fun initElements() {
        configureRecyclerView()

        shoplistPresenter.onInit()
    }

    private fun configureRecyclerView(): Unit {
        cestaRecyclerAdapter = CestaRecyclerAdapter(
            shoplistPresenter::onDeleteItemClick,
            shoplistPresenter::onItemClick
        )

        shoppinglist_rv.run {
            adapter = cestaRecyclerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun bindPresenter(): BaseContract.Presenter = this.shoplistPresenter

    override fun loadCesta(productos: List<Producto>) {
        cestaRecyclerAdapter.loadList(productos)
    }

    override fun setTotalPrice(price: Float) {
        total_price_text.text = getString(
            R.string.text_price,
            Mapper.map(price)
        )
    }
}
