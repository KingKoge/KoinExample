package me.suttichai.develop.koinexample

import android.app.Application
import me.suttichai.develop.koinexample.AppModule.databaseModule
import me.suttichai.develop.koinexample.AppModule.networkModule
import me.suttichai.develop.koinexample.AppModule.presenterModule
import me.suttichai.develop.koinexample.AppModule.repositoryModule
import me.suttichai.develop.koinexample.AppModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger(Level.DEBUG)
            modules(
                viewModelModule,
                presenterModule,
                networkModule,
                databaseModule,
                repositoryModule
            )
        }
    }
}