package com.ismaelgr.winkle.domain.usecase

class GetCountProductInCestaUseCase(
    private val getMyCestaUseCase: GetMyCestaUseCase
) {

    fun execute(idProduct: String, onSuccess: (Int) -> Unit, onError: (String) -> Unit) {
        getMyCestaUseCase.execute(
            onSuccess = { listProducts ->
                onSuccess(listProducts.products.filter { it == idProduct }.size)
            },
            onError
        )
    }

    fun dispose() {
        getMyCestaUseCase.dispose()

    }
}