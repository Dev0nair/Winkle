package com.ismaelgr.winkle.presentation.newproduct

import android.net.Uri
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.domain.usecase.CreateProductUseCase
import com.ismaelgr.winkle.domain.usecase.GetActualProfileUseCase
import com.ismaelgr.winkle.domain.usecase.SaveProductUseCase
import com.ismaelgr.winkle.domain.usecase.UploadImagesUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter
import com.ismaelgr.winkle.util.Mapper

class NewProductPresenter(
    private val view: NewProductContract.View,
    private val getActualProfileUseCase: GetActualProfileUseCase,
    private val saveProductUseCase: SaveProductUseCase,
    private val createProductUseCase: CreateProductUseCase,
    private val uploadImagesUseCase: UploadImagesUseCase
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
            loadDescriptionImage(*productoEdition.images.toTypedArray())
            loadEtiquetas(productoEdition.etiquetas)
            loadCategoria(Mapper.map(productoEdition.categoria))
            loadDisabledOnNextBuy(productoEdition.disableNextBuy)
            loadEnabled(productoEdition.activo)
        }
    }

    override fun onAddBigImageClick() {
        view.chooseImages(false)
    }

    override fun onNewNameInserted(name: String) {
        this.productoEdition.nombre = name
    }

    override fun onNewDescriptionInserted(name: String) {
        this.productoEdition.descripcion = name
    }

    override fun onAddDescriptionImageClick() {
        view.chooseImages(true)
    }

    override fun onDisabledOnNextBuyClik() {
        this.productoEdition.disableNextBuy = !this.productoEdition.disableNextBuy
        view.loadDisabledOnNextBuy(this.productoEdition.disableNextBuy)
    }

    override fun onSaveClick() {
        showLoading(true)
        if (newProduct) {
            getActualProfileUseCase.execute({ myProfile ->
                productoEdition.vendedorId = myProfile.id
                uploadLocalMainImage {
                    uploadLocalDescriptionImages {
                        createProductUseCase.execute(
                            productoEdition,
                            onSuccess = this::onFinishSaveOrCreate,
                            onError = this::showError
                        )
                    }
                }

            }, ::showError)
        } else {
            uploadLocalMainImage {
                uploadLocalDescriptionImages {
                    saveProductUseCase.execute(
                        productoEdition,
                        onComplete = this::onFinishSaveOrCreate,
                        onError = this::showError
                    )
                }
            }
        }
    }

    private fun uploadLocalDescriptionImages(then: () -> Unit) {
        uploadImagesUseCase.execute(
            idProfile = productoEdition.vendedorId,
            onAllSuccess = { list ->
                if (list.isNotEmpty()) {
                    productoEdition.images.run {
                        removeAll { it.startsWith("local:") }
                        addAll(list)
                    }
                }
                then()
            },
            onError = { error ->
                showError(error)
            },
            imagesToUpload = productoEdition.images.filter { it.startsWith("local:") }
                .toTypedArray()
        )
    }

    private fun uploadLocalMainImage(then: () -> Unit) {
        if (productoEdition.mainImage.startsWith("local:")) {
            uploadImagesUseCase.execute(
                idProfile = productoEdition.vendedorId,
                onAllSuccess = { list ->
                    productoEdition.mainImage = if (list.isNotEmpty()) list[0] else ""
                    then()
                },
                onError = { error ->
                    productoEdition.mainImage = ""
                    showError(error)
                },
                imagesToUpload = arrayOf(productoEdition.mainImage)
            )
        } else {
            then()
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

    override fun onDisableNextBuyChanged(boolean: Boolean) {
        productoEdition.disableNextBuy = boolean
    }

    override fun onEnabledChanged(boolean: Boolean) {
        productoEdition.activo = boolean
    }

    override fun onNewLocalImageInserted(imageUris: ArrayList<Uri>) {
        productoEdition.images.addAll(imageUris.map { "local:$it" })
        view.loadDescriptionImage(*productoEdition.images.toTypedArray().clone())
    }

    override fun onNewBigImageInserted(uri: Uri) {
        productoEdition.mainImage = "local:$uri"
        view.loadBigImage(productoEdition.mainImage)
    }

    private fun onFinishSaveOrCreate() {
        showLoading(false)
        if (newProduct) {
            newProduct = false
            showMsg("Se ha creado correctamente!")
        } else {
            showMsg("Se ha guardado correctamente!")
        }
    }

}