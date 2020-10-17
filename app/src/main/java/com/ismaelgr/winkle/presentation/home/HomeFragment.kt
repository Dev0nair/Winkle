package com.ismaelgr.winkle.presentation.home

import android.util.Log
import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.factory.ProductsRepositoryFactory
import com.ismaelgr.winkle.domain.usecase.GetAllProductsUseCase

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(R.layout.fragment_home), HomeContract.View {

    private lateinit var homePresenter: HomeContract.Presenter

    override fun loadProducts(list: List<Producto>) {
        Log.i("", "")
    }

    override fun initElements() {
        homePresenter =
            HomePresenter(
                this as HomeContract.View,
                GetAllProductsUseCase(ProductsRepositoryFactory().getRepository())
            )

        homePresenter.onInit()
    }
}
