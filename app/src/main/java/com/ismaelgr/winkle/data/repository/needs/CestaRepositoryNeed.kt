package com.ismaelgr.winkle.data.repository.needs

import com.ismaelgr.winkle.data.entity.Cesta

interface CestaRepositoryNeed {

    fun getCesta(idProfile: String, onLoad: (Cesta) -> Unit, onError: (String) -> Unit)

    fun addToCesta(idProfile: String, idProduct: String, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun clearCesta(idProfile: String, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun deleteFromCesta(idProfile: String, idProduct: String, onSuccess: () -> Unit, onError: (String) -> Unit)
}