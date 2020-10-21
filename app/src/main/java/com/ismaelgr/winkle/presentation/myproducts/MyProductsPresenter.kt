package com.ismaelgr.winkle.presentation.myproducts

import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.domain.usecase.GetMyProductsUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class MyProductsPresenter(
    private val myProds: MyProductsContract.View,
    private val getMyProductsUseCase: GetMyProductsUseCase
) :
    BasePresenter<MyProductsContract.View>(myProds), MyProductsContract.Presenter {

    private var categorias = ArrayList<Categorias>()

    override fun onInit() {
        showLoading(true)
        getMyProductsUseCase.execute(
            onSuccess = { list ->
                list.run(myProds::loadProducts)
                showLoading(false)
            },
            onError = {
                showLoading(false)
                showError(it)
            }
        )
    }

    override fun onProductClick(producto: Producto) {
        myProds.navigateToProductDetail(producto)
    }

    override fun onCategorySelected(categoria: Categorias) {
        if (categorias.contains(categoria)) {
            categorias.remove(categoria)
        } else {
            categorias.add(categoria)
        }

        myProds.filterCategories(categorias.toList())
    }

    override fun onSearch(search: String) {
        val tags = search.split("\"")
        var realTags = emptyList<String>()
        var nameDesc = ""

        if (tags.size > 1) {
            realTags = tags.subList(1, tags.size - 1).filter { pos -> pos.isNotEmpty() }
            nameDesc += "${tags[0]} ${tags[tags.size - 1]}"
        } else {
            nameDesc += search
        }

        myProds.run {
            filterTags(realTags) // si hay un 'Texto ejemplo "hola" nosek', será [Texto ejemplo, hola, nosek]. de esta forma, solo cogemos hola
            filterNameDesc(nameDesc)
        }
    }

}