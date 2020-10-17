package com.ismaelgr.winkle.data.repository.factory

import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.RepositoryOptions
import com.ismaelgr.winkle.data.repository.local.ProfileRepository as Local
import com.ismaelgr.winkle.data.repository.firebase.ProfileRepository as Network
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed

class ProfileRepositoryFactory {

    fun getRepository(repositoryOptions: RepositoryOptions = Configuration.PROFILE_USAGE): ProfileRepositoryNeed =
        when(repositoryOptions){
            RepositoryOptions.NETWORK -> Network()
            else -> Local()
        }
}