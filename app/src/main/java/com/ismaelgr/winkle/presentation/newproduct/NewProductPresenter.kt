package com.ismaelgr.winkle.presentation.newproduct

import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BasePresenter
import com.ismaelgr.winkle.util.Mapper

class NewProductPresenter(private val view: NewProductContract.View) :
    BasePresenter<NewProductContract.View>(view), NewProductContract.Presenter {

    private lateinit var productoEdition: Producto

    override fun onInit(producto: Producto?) {
        view.loadCategories(Categorias.values().toList())
        if(producto != null) {
            productoEdition = producto
            loadDataFromProduct()
        } else {
            productoEdition = Producto()
            selectDefaultValueCategory()
        }

        view.enableSaveCreateButton()
    }

    private fun selectDefaultValueCategory() {
        view.loadCategoria(Categorias.OTROS)
    }

    private fun loadDataFromProduct() {
        view.run {
            loadBigImage(productoEdition.mainImage)
            loadName(productoEdition.nombre)
            loadDescription(productoEdition.descripcion)
            loadPrice(productoEdition.precio)
            productoEdition.images.forEach { image ->
                loadDescriptionImage(image)
            }
            loadEtiquetas(productoEdition.etiquetas)
            loadCategoria(Mapper.map(productoEdition.categoria))
        }
    }

    override fun onAddBigImageClick() {
        TODO("Not yet implemented")
    }

    override fun onNewNameInserted(name: String) {
        this.productoEdition.nombre = name
    }

    override fun onNewDescriptionInserted(name: String) {
        this.productoEdition.descripcion = name
    }

    override fun onAddDescriptionImageClick() {
        TODO("Not yet implemented")
    }

    override fun onDisabledOnNextBuyClik() {
        this.productoEdition.disableNextBuy = !this.productoEdition.disableNextBuy
        view.loadDisabledOnNextBuy(this.productoEdition.disableNextBuy)
    }

    override fun onSaveClick() {
        TODO("Not yet implemented")
    }

}