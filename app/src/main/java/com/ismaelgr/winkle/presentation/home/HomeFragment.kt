package com.ismaelgr.winkle.presentation.home

import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(R.layout.fragment_home), HomeContract.View {

    private val homePresenter: HomeContract.Presenter by inject<HomePresenter> { parametersOf(this) }
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter

    override fun initElements() {
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
            viewChip.setChipBackgroundColorResource(R.color.chip_background)
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

    override fun reloadData() {
        homePresenter.onInit()
    }

    override fun bindPresenter(): BaseContract.Presenter = this.homePresenter

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

    override fun refreshFilters(list: List<Categorias>) {
        for(v in home_categories_cg.children){
            if(v is Chip){
                if(list.any{ it.name == v.text.toString().toUpperCase()}) {
                    v.isChecked = true
                }
            }
        }
        filterCategories(list)
    }

    override fun navigateToProductDetail(producto: Producto) {
        findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment, bundleOf("producto" to producto))
    }

    override fun navigateToProductEdition(producto: Producto) {
        findNavController().navigate(R.id.action_homeFragment_to_newProductFragment, bundleOf("producto" to producto))
    }
}
