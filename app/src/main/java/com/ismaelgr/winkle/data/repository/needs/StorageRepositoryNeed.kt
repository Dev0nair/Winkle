package com.ismaelgr.winkle.data.repository.needs

import io.reactivex.rxjava3.core.Single

interface StorageRepositoryNeed {

    /**
     * Returns the download url of the uploaded file
     * */
    fun uploadFile(idProfile: String, localFilePath: String): Single<String>
}