package com.ismaelgr.winkle.presentation.shoplist

import com.ismaelgr.winkle.data.entity.CestaProduct
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract

interface ShopListContract {

    interface View : BaseContract.View {
        fun loadCesta(productos: List<CestaProduct>)
        fun setTotalPrice(price: Float)
        fun navigateToProductDetails(producto: Producto)
    }
    interface Presenter : BaseContract.Presenter {
        fun onInit()
        fun onItemClick(producto: Producto)
        fun onDeleteItemClick(idProducto: String)
        fun onBuyBtnClick()
    }
}