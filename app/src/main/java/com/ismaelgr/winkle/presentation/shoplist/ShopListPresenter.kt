package com.ismaelgr.winkle.presentation.shoplist

import com.ismaelgr.winkle.domain.usecase.GetMyCestaUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class ShopListPresenter(
    private val shopList: ShopListContract.View,
    private val getMyCestaUseCase: GetMyCestaUseCase
) :
    BasePresenter<ShopListContract.View>(shopList), ShopListContract.Presenter {

    override fun onInit() {
        getMyCestaUseCase.execute(
            onSuccess = shopList::loadCesta,
            onError = this::showError
        )
    }

    override fun onItemClick() {
//        TODO("Not yet implemented")
    }

    override fun onBuyBtnClick() {
//        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        getMyCestaUseCase.dispose()
    }
}