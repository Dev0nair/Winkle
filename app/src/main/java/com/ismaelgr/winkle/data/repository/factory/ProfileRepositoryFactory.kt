package com.ismaelgr.winkle.data.repository.factory

import com.ismaelgr.winkle.data.repository.RepositoryOptions
import com.ismaelgr.winkle.data.repository.local.ProfileRepository as ProfileLocal
import com.ismaelgr.winkle.data.repository.firebase.ProfileRepository as ProfileNetwork
import com.ismaelgr.winkle.data.repository.needs.ProfileRepositoryNeed

class ProfileRepositoryFactory {

    fun getRepository(repositoryOptions: RepositoryOptions = RepositoryOptions.LOCAL): ProfileRepositoryNeed =
        when(repositoryOptions){
            RepositoryOptions.NETWORK -> ProfileNetwork()
            else -> ProfileLocal()
        }
}