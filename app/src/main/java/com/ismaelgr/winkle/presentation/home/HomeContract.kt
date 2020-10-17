package com.ismaelgr.winkle.presentation.home

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract

interface HomeContract {

    interface View : BaseContract.View {
        fun loadProducts(list: List<Producto>)
    }

    interface Presenter : BaseContract.Presenter {
        fun onInit()
        fun onProductClick(idProducto: String)
        fun onCategorySelected()
        fun onSearchTag(tag: String)
    }
}