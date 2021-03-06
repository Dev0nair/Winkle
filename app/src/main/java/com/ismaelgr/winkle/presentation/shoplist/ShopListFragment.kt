package com.ismaelgr.winkle.presentation.shoplist

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.CestaProduct
import com.ismaelgr.winkle.data.entity.Producto
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

    private fun configureRecyclerView() {
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

    override fun loadCesta(productos: List<CestaProduct>) {
        cestaRecyclerAdapter.loadList(productos)
    }

    override fun setTotalPrice(price: Float) {
        total_price_text.text = getString(
            R.string.text_price,
            Mapper.map(price)
        )
    }

    override fun navigateToProductDetails(producto: Producto) {
        findNavController().navigate(R.id.action_shopListFragment_to_productDetailsFragment, bundleOf("producto" to producto))
    }
}
