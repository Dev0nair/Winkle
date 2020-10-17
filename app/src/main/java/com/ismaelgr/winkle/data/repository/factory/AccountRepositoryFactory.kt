package com.ismaelgr.winkle.data.repository.factory

import com.ismaelgr.winkle.data.repository.RepositoryOptions
import com.ismaelgr.winkle.data.repository.firebase.AccountRepository as AccNetwork
import com.ismaelgr.winkle.data.repository.local.AccountRepository as AccLocal
import com.ismaelgr.winkle.data.repository.needs.AccountRepositoryNeed

class AccountRepositoryFactory {

    fun getRepository(repositoryOptions: RepositoryOptions = RepositoryOptions.LOCAL): AccountRepositoryNeed =
        when(repositoryOptions){
            RepositoryOptions.NETWORK -> AccNetwork()
            else -> AccLocal()
        }
}