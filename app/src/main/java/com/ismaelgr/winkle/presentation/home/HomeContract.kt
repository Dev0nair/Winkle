package com.ismaelgr.winkle.presentation.home

import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract

interface HomeContract {

    interface View : BaseContract.View {
        fun loadProducts(list: List<Producto>)
        fun filterTags(list: List<String>)
        fun filterCategories(list: List<Categorias>)
        fun filterNameDesc(string: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun onInit()
        fun onProductClick(idProducto: String)
        fun onCategorySelected(categoria: Categorias)
        fun onSearch(search: String)
    }
}