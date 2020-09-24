package com.example.retrofitrest

import android.app.Application
import com.example.retrofitrest.di.AppModule.adapterModule
import com.example.retrofitrest.di.AppModule.fragmentModule
import com.example.retrofitrest.di.AppModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    adapterModule,
                    fragmentModule
                )
            )
        }
    }
}