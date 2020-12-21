package com.ismaelgr.winkle.di

import com.ismaelgr.winkle.data.repository.factory.*
import com.ismaelgr.winkle.domain.usecase.*
import com.ismaelgr.winkle.presentation.home.HomeContract
import com.ismaelgr.winkle.presentation.home.HomePresenter
import com.ismaelgr.winkle.presentation.myproducts.MyProductsContract
import com.ismaelgr.winkle.presentation.myproducts.MyProductsPresenter
import com.ismaelgr.winkle.presentation.productdetails.ProductDetailsContract
import com.ismaelgr.winkle.presentation.productdetails.ProductDetailsPresenter
import com.ismaelgr.winkle.presentation.profile.ProfileContract
import com.ismaelgr.winkle.presentation.profile.ProfilePresenter
import com.ismaelgr.winkle.presentation.shoplist.ShopListContract
import com.ismaelgr.winkle.presentation.shoplist.ShopListPresenter
import org.koin.dsl.module

val presenterModules = module {
    factory { (view: HomeContract.View) ->
        HomePresenter(view, get(), get())
    }

    factory { (view: MyProductsContract.View) ->
        MyProductsPresenter(view, GetMyProductsUseCase(get(), get(), get()))
    }

    factory { (view: ShopListContract.View) ->
        ShopListPresenter(view, get())
    }

    factory { (view: ProfileContract.View) ->
        ProfilePresenter(view)
    }

    factory { (view: ProductDetailsContract.View) ->
        ProductDetailsPresenter(view, get(), get(), get(), get(), get())
    }
}

val useCaseModule = module {
    factory { GetAllProductsUseCase(get()) }
    factory { GetProductosMiCesta(get(), get()) }
    factory { GetMyProductsUseCase(get(), get(), get()) }
    factory { GetMyCestaUseCase(get(), get(), get()) }
    factory { HasProfileUseCase(get(), get()) }
    factory { GetProductOwnerUseCase(get()) }
    factory { IsMyProductUseCase(get()) }
    factory { GetCountProductInCestaUseCase(get()) }
    factory { AddProductToCestaUseCase(get(), get(), get()) }
    factory { HasReportedProductUseCase(get(), get(), get()) }
    factory { SendReportUseCase(get(), get(), get()) }
}

val repositoryModules = module {
    single { ProductsRepositoryFactory().getRepository() }
    single { ProfileRepositoryFactory().getRepository() }
    single { CestaRepositoryFactory().getRepository() }
    single { AccountRepositoryFactory().getRepository() }
    single { ReportsRepositoryFactory().getRepository() }
}