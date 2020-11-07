package com.ismaelgr.winkle.presentation.shoplist

import com.ismaelgr.winkle.domain.usecase.GetProductosMiCesta
import com.ismaelgr.winkle.presentation.base.BasePresenter

class ShopListPresenter(
    private val shopList: ShopListContract.View,
    private val getProductosMiCesta: GetProductosMiCesta
) :
    BasePresenter<ShopListContract.View>(shopList), ShopListContract.Presenter {

    override fun onInit() {
        getProductosMiCesta.execute(
            onSuccess = { productos ->
                shopList.run {
                    loadCesta(productos)
                    setTotalPrice(productos.sumByDouble { it.precio.toDouble() }.toFloat())
                }
            },
            onError = this::showError
        )
    }

    override fun onItemClick(idProducto: String) {
//        TODO("Not yet implemented")
    }

    override fun onDeleteItemClick(idProducto: String) {
//        TODO("Not yet implemented")
    }

    override fun onBuyBtnClick() {
//        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        getProductosMiCesta.dispose()
    }
}