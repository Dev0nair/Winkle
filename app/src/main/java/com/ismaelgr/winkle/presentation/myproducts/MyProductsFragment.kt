package com.ismaelgr.winkle.presentation.myproducts

import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.factory.AccountRepositoryFactory
import com.ismaelgr.winkle.data.repository.factory.ProductsRepositoryFactory
import com.ismaelgr.winkle.data.repository.factory.ProfileRepositoryFactory
import com.ismaelgr.winkle.domain.usecase.GetMyProductsUseCase
import com.ismaelgr.winkle.presentation.base.BaseContract
import com.ismaelgr.winkle.presentation.home.HomeRecyclerAdapter
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.fragment_myproducts.*

/**
 * A simple [Fragment] subclass.
 */
class MyProductsFragment : BaseFragment(R.layout.fragment_myproducts), MyProductsContract.View {

    private lateinit var myproductsPresenter: MyProductsContract.Presenter
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter

    override fun bindPresenter(): BaseContract.Presenter = this.myproductsPresenter

    override fun initElements() {
        myproductsPresenter =
            MyProductsPresenter(
                this as MyProductsContract.View,
                GetMyProductsUseCase(
                    AccountRepositoryFactory().getRepository(),
                    ProfileRepositoryFactory().getRepository(),
                    ProductsRepositoryFactory().getRepository()
                )
            )
        homeRecyclerAdapter = HomeRecyclerAdapter { idProducto ->
            myproductsPresenter.onProductClick(idProducto)
        }

        myProducts_products_rv.run {
            adapter = homeRecyclerAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        for (category in Categorias.values()) {
            val viewChip = Chip(myProducts_categories_cg.context)
            viewChip.text = Mapper.map(requireContext(), category)
            viewChip.isClickable = true
            viewChip.isCheckable = true
            viewChip.setChipBackgroundColorResource(R.color.chip_background)
            viewChip.setOnClickListener { myproductsPresenter.onCategorySelected(category) }

            myProducts_categories_cg.addView(viewChip)
        }

        myProducts_search_product_sv.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.run(myproductsPresenter::onSearch)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (myProducts_search_product_sv.query.isEmpty()) "".run(myproductsPresenter::onSearch)
                return false
            }
        })

        myproductsPresenter.onInit()
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

    override fun navigateToProductDetail(producto: Producto) {
        findNavController().navigate(
            R.id.action_myProductsFragment_to_productDetailsFragment,
            bundleOf("producto" to producto)
        )
    }
}
