package com.ismaelgr.winkle.presentation.home

import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.factory.ProductsRepositoryFactory
import com.ismaelgr.winkle.domain.usecase.GetAllProductsUseCase
import com.ismaelgr.winkle.util.Mapper
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

        for (category in Categorias.values()) {
            val viewChip = Chip(home_categories_cg.context)
            viewChip.text = Mapper.map(requireContext(), category)
            viewChip.isClickable = true
            viewChip.isCheckable = true
            viewChip.setOnClickListener { homePresenter.onCategorySelected(category) }

            home_categories_cg.addView(viewChip)
        }

        home_search_product_sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.run(homePresenter::onSearch)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (home_search_product_sv.query.isEmpty()) "".run(homePresenter::onSearch)
                return false
            }
        })

        homePresenter.onInit()
    }

    override fun loadProducts(list: List<Producto>) {
        homeRecyclerAdapter.setList(list)
    }

    override fun filterTags(list: List<String>) {
        homeRecyclerAdapter.run {
            setTags(list)
            setFilters()
        }
    }

    override fun filterCategories(list: List<Categorias>) {
        homeRecyclerAdapter.run {
            setCategories(list)
            setFilters()
        }
    }

    override fun filterNameDesc(string: String) {
        homeRecyclerAdapter.run {
            setNameDesc(string)
            setFilters()
        }
    }
}
