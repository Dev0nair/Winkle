package com.ismaelgr.winkle.data.repository.factory

import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.RepositoryOptions
import com.ismaelgr.winkle.data.repository.needs.ReportsRepositoryNeed
import com.ismaelgr.winkle.data.repository.firebase.ReportsRepository as NetworkRepo
import com.ismaelgr.winkle.data.repository.local.ReportsRepository as LocalRepo

class ReportsRepositoryFactory {

    fun getRepository(repositoryOptions: RepositoryOptions = Configuration.PROFILE_USAGE): ReportsRepositoryNeed =
        when(repositoryOptions){
            RepositoryOptions.NETWORK -> NetworkRepo()
            else -> LocalRepo()
        }

}