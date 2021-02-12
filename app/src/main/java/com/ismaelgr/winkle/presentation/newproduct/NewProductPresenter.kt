package com.ismaelgr.winkle.presentation.newproduct

import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.domain.usecase.CreateProductUseCase
import com.ismaelgr.winkle.domain.usecase.GetActualProfileUseCase
import com.ismaelgr.winkle.domain.usecase.SaveProductUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter
import com.ismaelgr.winkle.util.Mapper

class NewProductPresenter(
    private val view: NewProductContract.View,
    private val getActualProfileUseCase: GetActualProfileUseCase,
    private val saveProductUseCase: SaveProductUseCase,
    private val createProductUseCase: CreateProductUseCase
) :
    BasePresenter<NewProductContract.View>(view), NewProductContract.Presenter {

    private lateinit var productoEdition: Producto
    private var newProduct: Boolean = true

    override fun onInit(producto: Producto?) {
        view.loadCategories(Categorias.values().toList())
        if (producto != null) {
            productoEdition = producto
            loadDataFromProduct()
        } else {
            productoEdition = Producto()
            selectDefaultValueCategory()
        }

        view.run {
            enableSaveCreateButton()
            setChangeListeners()
        }
    }

    private fun selectDefaultValueCategory() {
        view.loadCategoria(Categorias.OTROS)
    }

    private fun loadDataFromProduct() {
        newProduct = false
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
        if (newProduct) {
            getActualProfileUseCase.execute({
                productoEdition.vendedorId = it.id

                createProductUseCase.execute(
                    productoEdition,
                    onSuccess = this::onFinishSaveOrCreate,
                    onError = this::showError
                )
            }, ::showError)
        } else {
            saveProductUseCase.execute(
                productoEdition,
                onComplete = this::onFinishSaveOrCreate,
                onError = this::showError
            )
        }
    }

    override fun onNewPriceInserted(price: String) {
        price.toFloatOrNull()?.let { p -> productoEdition.precio = p }
    }

    override fun onCategoryChanged(position: Int) {
        productoEdition.categoria = position
    }

    override fun onNewEtiquetasInserted(categorias: List<String>) {
        productoEdition.etiquetas = categorias
    }

    private fun onFinishSaveOrCreate() {
        if (newProduct) {
            showMsg("Se ha creado correctamente!")
        } else {
            showMsg("Se ha guardado correctamente!")
        }
    }

}