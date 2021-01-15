package com.ismaelgr.winkle.data.repository.factory

import com.ismaelgr.winkle.data.repository.Configuration
import com.ismaelgr.winkle.data.repository.RepositoryOptions
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import com.ismaelgr.winkle.data.repository.local.ProductRepository as Local
import com.ismaelgr.winkle.data.repository.firebase.ProductRepository as Network

class ProductsRepositoryFactory {

    fun getRepository(repositoryOptions: RepositoryOptions = Configuration.PRODUCTS_USAGE): ProductRepositoryNeed =
        when(repositoryOptions){
            RepositoryOptions.NETWORK -> Network()
            else -> Local()
        }
}