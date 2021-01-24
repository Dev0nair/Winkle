package com.ismaelgr.winkle.presentation.productdetails

import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.entity.Puntuacion
import com.ismaelgr.winkle.domain.usecase.*
import com.ismaelgr.winkle.presentation.base.BasePresenter

class ProductDetailsPresenter(
    private val view: ProductDetailsContract.View,
    private val getProductOwnerUseCase: GetProductOwnerUseCase,
    private val getCountProductInCestaUseCase: GetCountProductInCestaUseCase,
    private val addProductToCestaUseCase: AddProductToCestaUseCase,
    private val hasReportedProductUseCase: HasReportedProductUseCase,
    private val getRateUseCase: GetRateUseCase,
    private val reportUseCase: SendReportUseCase,
    private val getActualProfileUseCase: GetActualProfileUseCase
) :
    BasePresenter<ProductDetailsContract.View>(view), ProductDetailsContract.Presenter {

    private lateinit var producto: Producto

    override fun onInit(producto: Producto) {
        this.producto = producto
        view.run {
            setName(producto.nombre)
            setDescription(producto.descripcion)
            setImages(listOf(producto.mainImage) + producto.images)
            setPrice(producto.precio)
        }

        refreshProfileData()
        refreshCountOnCesta()
        refreshReported()
        refreshRating()
    }

    private fun refreshRating() {
        getRateUseCase.execute(producto.vendedorId, { view.setPuntuation(it) }, ::showError)
    }

    private fun refreshReported() {
        hasReportedProductUseCase.execute(
            producto.id,
            onSuccess = {
                if (it) {
                    view.setReported()
                } else {
                    view.setNotReported()
                }
            }, ::showError
        )
    }

    private fun refreshProfileData() {
        getProductOwnerUseCase.execute(
            producto.vendedorId,
            onLoad = { perfil ->
                view.run {
                    setImageProfile(perfil.image)
                    setNameProfile(perfil.username)
                }
            },
            onError = ::showError
        )
    }

    private fun refreshCountOnCesta() {
        getCountProductInCestaUseCase.execute(
            producto.id,
            onSuccess = { count ->
                view.setCountProduct(count)
            }, ::showError
        )
    }

    override fun onAddToShopListClick() {
        addProductToCestaUseCase.execute(
            producto.id,
            ::refreshCountOnCesta,
            ::showError
        )
    }

    override fun onReportClick() {
        view.run {
            enableReportButton(false)
            writeReasonReport()
        }
    }

    private fun getProfile(action: (Perfil) -> Unit){
        getActualProfileUseCase.execute(
            onSuccess = action,
            onError = ::showError
        )
    }

    override fun onViewProfileClick() {
        view.navigateToProfileDetails(producto.vendedorId)
    }

    override fun onDetailImageClick(url: String) {
        view.run {
            showBigImage(url)
            showBigImage(true)
        }
    }

    override fun onBackScreenClick() {
        view.showBigImage(false)
    }

    override fun sendReport(reason: String) {
        reportUseCase.execute(reason, producto.id, ::refreshReported, ::showError)
    }

    override fun onCancelReport() {
        view.enableReportButton(true)
    }

    override fun onDestroy() {
        getProductOwnerUseCase.dispose()
        getCountProductInCestaUseCase.dispose()
        getRateUseCase.dispose()
        addProductToCestaUseCase.dispose()
        getProductOwnerUseCase.dispose()
        hasReportedProductUseCase.dispose()
    }
}