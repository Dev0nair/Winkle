package com.ismaelgr.winkle.presentation.productdetails

import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.domain.usecase.AddProductToCestaUseCase
import com.ismaelgr.winkle.domain.usecase.GetCountProductInCestaUseCase
import com.ismaelgr.winkle.domain.usecase.GetProductOwnerUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class ProductDetailsPresenter(
    private val view: ProductDetailsContract.View,
    private val getProductOwnerUseCase: GetProductOwnerUseCase,
    private val getCountProductInCestaUseCase: GetCountProductInCestaUseCase,
    private val addProductToCestaUseCase: AddProductToCestaUseCase
) :
    BasePresenter<ProductDetailsContract.View>(view), ProductDetailsContract.Presenter {

    private lateinit var producto: Producto

    override fun onInit(producto: Producto) {
        this.producto = producto
        view.run {
            setName(producto.nombre)
            setDescription(producto.descripcion)
            setMainImage(producto.mainImage)
            setImages(producto.images)
            setPrice(producto.precio)

            refreshProfileData()
            refreshCountOnCesta()
        }
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
        TODO("Not yet implemented")
    }

    override fun onLikeClick() {
        TODO("Not yet implemented")
    }

    override fun onAlternateDisableOnBuyClick() {
        TODO("Not yet implemented")
    }

    override fun onViewProfileClick() {
        view.navigateToProfileDetails(producto.vendedorId)
    }

    override fun onDestroy() {
        getProductOwnerUseCase.dispose()
    }
}