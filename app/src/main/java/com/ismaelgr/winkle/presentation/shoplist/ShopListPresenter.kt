package com.ismaelgr.winkle.presentation.shoplist

import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.entity.CestaProduct
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.domain.usecase.GetProductsOfBasketUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class ShopListPresenter(
    private val shopList: ShopListContract.View,
    private val getProductsOfBasketUseCase: GetProductsOfBasketUseCase
) :
    BasePresenter<ShopListContract.View>(shopList), ShopListContract.Presenter {

    lateinit var cesta: List<Cesta>

    override fun onInit() {
        getProductsOfBasketUseCase.execute(
            onSuccess = { productos, cesta ->
                this.cesta = cesta
                shopList.run {
                    loadCesta(tratarCesta(productos.map { CestaProduct(it, 1) }))
                    setTotalPrice(productos.sumByDouble { it.precio.toDouble() }.toFloat())
                }
            },
            onError = this::showError
        )
    }

    private fun tratarCesta(list: List<CestaProduct>): List<CestaProduct>{
        val fullcesta = arrayListOf<CestaProduct>()

        list.forEach { cestaProduct ->
            if(fullcesta.any { it.producto.id == cestaProduct.producto.id }){
                val prevCestaProd = fullcesta.filter { it.producto.id == cestaProduct.producto.id }[0]
                prevCestaProd.count = prevCestaProd.count + 1
                fullcesta.removeAll { it.producto.id == cestaProduct.producto.id }
                fullcesta.add(prevCestaProd)
            } else {
                fullcesta.add(cestaProduct)
            }
        }

        return fullcesta
    }

    override fun onItemClick(producto: Producto) {
        shopList.navigateToProductDetails(producto)
    }

    override fun onDeleteItemClick(idProducto: String) {
        val cestaId = this.cesta.findLast { it.idProduct == idProducto }
        cesta.dropLastWhile { it.idProduct == idProducto }
        // TODO
    }

    override fun onBuyBtnClick() {
//        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        getProductsOfBasketUseCase.dispose()
    }
}