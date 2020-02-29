package com.example.testalbumapp.presentation

import androidx.multidex.MultiDexApplication
import com.example.testalbumapp.BuildConfig
import com.example.testalbumapp.presentation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}