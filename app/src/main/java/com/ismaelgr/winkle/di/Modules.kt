package com.ismaelgr.winkle.di

import com.ismaelgr.winkle.data.repository.factory.*
import com.ismaelgr.winkle.domain.usecase.*
import com.ismaelgr.winkle.presentation.home.HomeContract
import com.ismaelgr.winkle.presentation.home.HomePresenter
import com.ismaelgr.winkle.presentation.login.LoginContract
import com.ismaelgr.winkle.presentation.login.LoginPresenter
import com.ismaelgr.winkle.presentation.myproducts.MyProductsContract
import com.ismaelgr.winkle.presentation.myproducts.MyProductsPresenter
import com.ismaelgr.winkle.presentation.newproduct.NewProductContract
import com.ismaelgr.winkle.presentation.newproduct.NewProductPresenter
import com.ismaelgr.winkle.presentation.productdetails.ProductDetailsContract
import com.ismaelgr.winkle.presentation.productdetails.ProductDetailsPresenter
import com.ismaelgr.winkle.presentation.profile.ProfileContract
import com.ismaelgr.winkle.presentation.profile.ProfilePresenter
import com.ismaelgr.winkle.presentation.shoplist.ShopListContract
import com.ismaelgr.winkle.presentation.shoplist.ShopListPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val presenterModules = module {

    factory { (view: LoginContract.View) ->
        LoginPresenter(view, get(), get(), get())
    }

    factory { (view: HomeContract.View) ->
        HomePresenter(view, get(), get(), get())
    }

    factory { (view: MyProductsContract.View) ->
        MyProductsPresenter(view, get())
    }

    factory { (view: ShopListContract.View) ->
        ShopListPresenter(view, get())
    }

    factory { (view: ProfileContract.View) ->
        ProfilePresenter(view)
    }

    factory { (view: ProductDetailsContract.View) ->
        ProductDetailsPresenter(view, get(), get(), get(), get(), get(), get(), get())
    }

    factory { (view: NewProductContract.View) ->
        NewProductPresenter(view, get(), get(), get(), get())
    }
}

val useCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { GetAllProductsExceptMineUseCase(get()) }
    factory { GetProductsOfBasketUseCase(get(), get()) }
    factory { GetMyProductsUseCase(get(), get(), get()) }
    factory { GetMyCestaUseCase(get(), get(), get()) }
    factory { HasProfileUseCase(get(), get()) }
    factory { GetProductOwnerUseCase(get()) }
    factory { IsMyProductUseCase(get()) }
    factory { GetCountProductInCestaUseCase(get()) }
    factory { AddProductToCestaUseCase(get(), get(), get()) }
    factory { HasReportedProductUseCase(get(), get(), get()) }
    factory { SendReportUseCase(get(), get(), get()) }
    factory { GetRateUseCase(get()) }
    factory { RateProductUseCase(get()) }
    factory { GetActualProfileUseCase(get(), get()) }
    factory { SaveProductUseCase(get()) }
    factory { CreateProductUseCase(get()) }
    factory { UploadImagesUseCase(get()) }
    factory { SelectProfileUseCase(get(), get()) }
}

val repositoryModules = module {
    single { ProductsRepositoryFactory().getRepository() }
    single { ProfileRepositoryFactory(androidContext()).getRepository() }
    single { CestaRepositoryFactory().getRepository() }
    single { AccountRepositoryFactory().getRepository() }
    single { ReportsRepositoryFactory().getRepository() }
    single { RateRepositoryFactory().getRepository() }
    single { StorageRepositoryFactory().getRepository() }
}