package com.ismaelgr.winkle.data.repository.factory

import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.RepositoryOptions
import com.ismaelgr.winkle.data.repository.firebase.AccountLocalRepository as Network
import com.ismaelgr.winkle.data.repository.local.AccountRepository as Local
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed

class AccountRepositoryFactory {

    fun getRepository(repositoryOptions: RepositoryOptions = Configuration.ACCOUNT_USAGE): AccountRepositoryNeed =
        when(repositoryOptions){
            RepositoryOptions.NETWORK -> Network()
            else -> Local()
        }
}