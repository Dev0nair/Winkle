package com.ismaelgr.winkle.domain.usecase

class GetCountProductInCestaUseCase(
    private val getMyCestaUseCase: GetMyCestaUseCase
) {

    fun execute(idProduct: String, onSuccess: (Int) -> Unit, onError: (String) -> Unit) {
        getMyCestaUseCase.execute(
            onSuccess = { listProducts ->
                onSuccess(listProducts.filter { it.idProduct == idProduct }.size)
            },
            onError
        )
    }

    fun dispose() {
        getMyCestaUseCase.dispose()

    }
}