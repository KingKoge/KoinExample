package me.suttichai.develop.koinexample

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val databaseModule = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                DatabaseService::class.java,
                DatabaseConfig.DATABASE_NAME
            ).build()
        }
    }

    val repositoryModule = module {
        factory { CharacterRepository(get(), get()) }
    }

    val networkModule = module {
        factory { NetworkConfig() }
        factory { NetworkClient() }
        single { ApiService(get(), get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get(), get()) }
    }

    val presenterModule = module {
        factory { MainPresenter() }
    }
}