package com.ismaelgr.winkle.data.repository.factory

import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.RepositoryOptions
import com.ismaelgr.winkle.data.repository.firebase.StorageFirebaseRepository
import com.ismaelgr.winkle.data.repository.local.StorageLocalRepository
import com.ismaelgr.winkle.data.repository.needs.StorageRepositoryNeed

class StorageRepositoryFactory {
    fun getRepository(repositoryOptions: RepositoryOptions = Configuration.STORAGE_USAGE): StorageRepositoryNeed =
        when(repositoryOptions){
            RepositoryOptions.NETWORK -> StorageFirebaseRepository()
            else -> StorageLocalRepository()
        }
}