package com.ismaelgr.winkle.presentation.home

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.factory.ProductsRepositoryFactory
import com.ismaelgr.winkle.domain.usecase.GetAllProductsUseCase
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(R.layout.fragment_home), HomeContract.View {

    private lateinit var homePresenter: HomeContract.Presenter
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter

    override fun initElements() {
        homePresenter =
            HomePresenter(
                this as HomeContract.View,
                GetAllProductsUseCase(ProductsRepositoryFactory().getRepository())
            )

        homeRecyclerAdapter = HomeRecyclerAdapter { idProducto ->
            homePresenter.onProductClick(idProducto)
        }

        home_products_rv.run {
            adapter = homeRecyclerAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        homePresenter.onInit()
    }

    override fun loadProducts(list: List<Producto>) {
        homeRecyclerAdapter.setList(list)
    }
}
