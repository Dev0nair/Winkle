package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Cesta
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed

class CestaRepository: CestaRepositoryNeed {

    val cesta = Cesta(
        id = "1",
        products = arrayListOf("1", "2", "3")
    )

    override fun getCesta(idProfile: String, onLoad: (Cesta) -> Unit, onError: (String) -> Unit) {
        cesta.run(onLoad)
    }

    override fun addToCesta(idProfile: String, idProduct: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        cesta.products.add(idProduct)
        onSuccess()
    }

    override fun clearCesta(idProfile: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        cesta.products.clear()
        onSuccess()
    }

    override fun deleteFromCesta(
        idProfile: String,
        idProduct: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if(cesta.products.contains(idProduct)) {
            cesta.products.remove(idProduct)
            onSuccess()
        } else {
            onError("No se pudo borrar un producto que no existe")
        }
    }
}