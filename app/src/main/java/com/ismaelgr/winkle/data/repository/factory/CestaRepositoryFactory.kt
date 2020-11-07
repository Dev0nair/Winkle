package com.ismaelgr.winkle.data.repository.factory

import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.RepositoryOptions
import com.ismaelgr.winkle.data.repository.needs.CestaRepositoryNeed
import com.ismaelgr.winkle.data.repository.firebase.CestaRepository as Network
import com.ismaelgr.winkle.data.repository.local.CestaRepository as Local

class CestaRepositoryFactory {
    fun getRepository(repositoryOptions: RepositoryOptions = Configuration.CESTA_USAGE): CestaRepositoryNeed =
        when(repositoryOptions){
            RepositoryOptions.NETWORK -> Network()
            else -> Local()
        }
}