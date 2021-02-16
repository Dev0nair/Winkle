package com.ismaelgr.winkle.presentation.newproduct

import android.net.Uri
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract

interface NewProductContract {

    interface View : BaseContract.View {
        fun loadBigImage(url: String)
        fun loadName(name: String)
        fun loadDescription(description: String)
        fun loadDescriptionImage(vararg url: String)
        fun loadDisabledOnNextBuy(disableNextBuy: Boolean)
        fun loadPrice(float: Float)
        fun enableSaveCreateButton()
        fun loadCategories(categorias: List<Categorias>)
        fun loadCategoria(categoria: Categorias)
        fun loadEtiquetas(etiquetas: List<String>)
        fun setChangeListeners()
        fun loadEnabled(boolean: Boolean)
        fun chooseImages(multiple: Boolean)
    }

    interface Presenter : BaseContract.Presenter {
        fun onInit(producto: Producto?)
        fun onAddBigImageClick()
        fun onNewNameInserted(name: String)
        fun onNewDescriptionInserted(name: String)
        fun onAddDescriptionImageClick()
        fun onDisabledOnNextBuyClik()
        fun onSaveClick()
        fun onNewPriceInserted(price: String)
        fun onCategoryChanged(position: Int)
        fun onNewEtiquetasInserted(categorias: List<String>)
        fun onDisableNextBuyChanged(boolean: Boolean)
        fun onEnabledChanged(boolean: Boolean)
        fun onNewLocalImageInserted(imageUris: ArrayList<Uri>)
        fun onNewBigImageInserted(uri: Uri)
    }
}