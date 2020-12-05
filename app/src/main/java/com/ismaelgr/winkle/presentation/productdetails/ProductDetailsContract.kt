package com.ismaelgr.winkle.presentation.productdetails

import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract

interface ProductDetailsContract {

    interface View : BaseContract.View {
        fun setMainImage(url: String)
        fun setName(name: String)
        fun setPrice(price: Float)
        fun setDescription(description: String)
        fun setImages(images: List<String>)
        fun setNumberOnShopList(count: Int)
        fun setPuntuation(puntuation: String)
        fun setImageProfile(url: String)
        fun setNameProfile(name: String)
        fun setHasFav(hasFav: Boolean)
        fun navigateToProfileDetails(idPerfil: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun onInit(producto: Producto)
        fun onAddToShopListClick()
        fun onReportClick()
        fun onLikeClick()
        fun onAlternateDisableOnBuyClick()
        fun onViewProfileClick()
    }
}