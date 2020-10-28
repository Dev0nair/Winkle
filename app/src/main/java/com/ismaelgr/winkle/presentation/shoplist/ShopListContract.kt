package com.ismaelgr.winkle.presentation.shoplist

import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.presentation.base.BaseContract

interface ShopListContract {

    interface View : BaseContract.View {
        fun loadCesta(cesta: Cesta)
    }
    interface Presenter : BaseContract.Presenter {
        fun onInit()
        fun onItemClick()
        fun onBuyBtnClick()
    }
}