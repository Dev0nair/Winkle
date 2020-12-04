package com.ismaelgr.winkle.domain.usecase

class IsMyProductUseCase(
    private val myProductsUseCase: GetMyProductsUseCase
) {

    fun execute(idProduct: String, onSuccess: (Boolean) -> Unit, onError: (String) -> Unit) {
        myProductsUseCase.execute(
            onSuccess = { listMyProducts ->
                onSuccess(listMyProducts.any { it.id == idProduct })
            },
            onError
        )
    }

    fun dispose() {
        myProductsUseCase.dispose()
    }
}