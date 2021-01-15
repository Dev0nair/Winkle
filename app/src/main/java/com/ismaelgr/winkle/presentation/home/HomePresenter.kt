package com.ismaelgr.winkle.presentation.home

import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.domain.usecase.GetAllProductsExceptMineUseCase
import com.ismaelgr.winkle.domain.usecase.IsMyProductUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class HomePresenter(
    private val home: HomeContract.View,
    private val getAllProductsExceptMineUseCase: GetAllProductsExceptMineUseCase,
    private val isMyProductUseCase: IsMyProductUseCase
) :
    BasePresenter<HomeContract.View>(home), HomeContract.Presenter {

    private var categorias = ArrayList<Categorias>()

    override fun onInit() {
        showLoading(true)
        getAllProductsExceptMineUseCase.execute(
            onSuccess = { list ->
                home.run {
                    loadProducts(list)
                    refreshFilters(categorias)
                }
                showLoading(false)
            },
            onError = {
                showLoading(false)
                showError(it)
            }
        )
    }

    override fun onProductClick(producto: Producto) {
        isMyProductUseCase.execute(
            producto.id,
            onSuccess = { imOwner ->
                if (imOwner) home.navigateToProductEdition(producto)
                else home.navigateToProductDetail(producto)
            },
            ::showError
        )
    }

    override fun onCategorySelected(categoria: Categorias) {
        if (categorias.contains(categoria)) {
            categorias.remove(categoria)
        } else {
            categorias.add(categoria)
        }

        home.filterCategories(categorias.toList())
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

        home.run {
            filterTags(realTags) // si hay un 'Texto ejemplo "hola" nosek', será [Texto ejemplo, hola, nosek]. de esta forma, solo cogemos hola
            filterNameDesc(nameDesc)
        }
    }

    override fun onDestroy() {
        getAllProductsExceptMineUseCase.dispose()
        isMyProductUseCase.dispose()
    }
}