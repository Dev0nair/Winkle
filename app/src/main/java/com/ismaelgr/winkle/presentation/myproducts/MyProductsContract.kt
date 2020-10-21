package com.ismaelgr.winkle.presentation.myproducts

import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract

interface MyProductsContract {

    interface View : BaseContract.View {
        fun loadProducts(list: List<Producto>)
        fun filterTags(list: List<String>)
        fun filterCategories(list: List<Categorias>)
        fun filterNameDesc(string: String)
        fun navigateToProductDetail(producto: Producto)
    }

    interface Presenter : BaseContract.Presenter {
        fun onInit()
        fun onProductClick(producto: Producto)
        fun onCategorySelected(categoria: Categorias)
        fun onSearch(search: String)
    }
}