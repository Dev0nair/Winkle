package com.ismaelgr.winkle.presentation.productdetails

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BasePresenter

class ProductDetailsPresenter(private val view: ProductDetailsContract.View) :
    BasePresenter<ProductDetailsContract.View>(view), ProductDetailsContract.Presenter {

    override fun onInit(producto: Producto) {
        view.run {
            setName(producto.nombre)
            setDescription(producto.descripcion)
            setMainImage(producto.mainImage)
            setImages(producto.images)
            setPrice(producto.precio)
        }
    }

    override fun onAddToShopListClick() {
        TODO("Not yet implemented")
    }

    override fun onReportClick() {
        TODO("Not yet implemented")
    }

    override fun onLikeClick() {
        TODO("Not yet implemented")
    }

    override fun onAlternateDisableOnBuyClick() {
        TODO("Not yet implemented")
    }

}