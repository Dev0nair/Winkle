package com.ismaelgr.winkle.presentation.home

import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.domain.usecase.GetAllProductsUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class HomePresenter(
    private val view: HomeContract.View,
    private val getAllProductsUseCase: GetAllProductsUseCase
) :
    BasePresenter<HomeContract.View>(view), HomeContract.Presenter {

    private var categorias = ArrayList<Categorias>()

    override fun onInit() {
        showLoading(true)
        getAllProductsUseCase.execute(
            onSuccess = { list ->
                list.run(view::loadProducts)
                showLoading(false)
            },
            onError = {
                showLoading(false)
                showError(it)
            }
        )
    }

    override fun onProductClick(idProducto: String) {
        // TODO("Not yet implemented")
    }

    override fun onCategorySelected(categoria: Categorias) {
        if(categorias.contains(categoria)){
            categorias.remove(categoria)
        } else {
            categorias.add(categoria)
        }

        view.filterCategories(categorias.toList())
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

        view.run {
            filterTags(realTags) // si hay un 'Texto ejemplo "hola" nosek', será [Texto ejemplo, hola, nosek]. de esta forma, solo cogemos hola
            filterNameDesc(nameDesc)
        }
    }
}