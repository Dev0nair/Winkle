package com.ismaelgr.winkle.presentation.shoplist

import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract

interface ShopListContract {

    interface View : BaseContract.View {
        fun loadCesta(productos: List<Producto>)
    }
    interface Presenter : BaseContract.Presenter {
        fun onInit()
        fun onItemClick(idProducto: String)
        fun onDeleteItemClick(idProducto: String)
        fun onBuyBtnClick()
    }
}