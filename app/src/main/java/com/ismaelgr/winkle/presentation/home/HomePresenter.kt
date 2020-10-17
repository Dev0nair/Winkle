package com.ismaelgr.winkle.presentation.home

import com.ismaelgr.winkle.domain.usecase.GetAllProductsUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class HomePresenter(private val view: HomeContract.View, private val getAllProductsUseCase: GetAllProductsUseCase) :
    BasePresenter<HomeContract.View>(view), HomeContract.Presenter {

    override fun onInit() {
        getAllProductsUseCase.execute(
            onSuccess = { list -> list.run(view::loadProducts)},
            onError = { showError(it) }
        )
    }

    override fun onProductClick(idProducto: String) {
        // TODO("Not yet implemented")
    }

    override fun onCategorySelected() {
        // TODO("Not yet implemented")
    }

    override fun onSearchTag(tag: String) {
        // TODO("Not yet implemented")
    }
}