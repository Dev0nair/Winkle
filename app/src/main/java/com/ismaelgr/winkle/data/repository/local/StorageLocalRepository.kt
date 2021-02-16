package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.repository.needs.StorageRepositoryNeed
import io.reactivex.rxjava3.core.Single

class StorageLocalRepository: StorageRepositoryNeed {
    override fun uploadFile(idProfile: String, localFilePath: String): Single<String> =
        Single.just("local:$localFilePath")
}