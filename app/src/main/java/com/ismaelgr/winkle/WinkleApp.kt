package com.ismaelgr.winkle

import android.app.Application
import android.util.Log
import com.ismaelgr.winkle.di.presenterModules
import com.ismaelgr.winkle.di.repositoryModules
import com.ismaelgr.winkle.di.useCaseModule
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WinkleApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(repositoryModules, useCaseModule, presenterModules))
        }
        RxJavaPlugins.setErrorHandler { Log.e("Error Rx", it.message.toString()) }
    }
}