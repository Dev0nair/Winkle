package com.ismaelgr.winkle.data.repository.factory

import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.RepositoryOptions
import com.ismaelgr.winkle.data.repository.local.RateRepository as Local
import com.ismaelgr.winkle.data.repository.firebase.RateRepository as Network
import com.ismaelgr.winkle.data.repository.needs.RateRepositoryNeed

class RateRepositoryFactory {
    fun getRepository(repositoryOptions: RepositoryOptions = Configuration.RATE_USAGE): RateRepositoryNeed =
        when(repositoryOptions){
            RepositoryOptions.NETWORK -> Network()
            else -> Local()
        }
}