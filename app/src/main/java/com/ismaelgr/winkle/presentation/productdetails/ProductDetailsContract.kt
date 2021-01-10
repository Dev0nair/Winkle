package com.ismaelgr.winkle.presentation.productdetails

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract

interface ProductDetailsContract {

    interface View : BaseContract.View {
        fun setMainImage(url: String)
        fun setName(name: String)
        fun setPrice(price: Float)
        fun setDescription(description: String)
        fun setImages(images: List<String>)
        fun setPuntuation(puntuation: Float)
        fun setImageProfile(url: String)
        fun setNameProfile(name: String)
        fun setCountProduct(count: Int)
        fun navigateToProfileDetails(idPerfil: String)
        fun showBigImage(url: String)
        fun showBigImage(show: Boolean)
        fun writeReasonReport()
        fun enableReportButton(boolean: Boolean)
        fun setReported()
        fun setNotReported()
    }

    interface Presenter : BaseContract.Presenter {
        fun onInit(producto: Producto)
        fun onAddToShopListClick()
        fun onReportClick()
        fun onLikeClick()
        fun onViewProfileClick()
        fun onDetailImageClick(url: String)
        fun onBackScreenClick()
        fun sendReport(reason: String)
        fun onCancelReport()
    }
}