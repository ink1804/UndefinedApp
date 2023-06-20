package com.ink1804.dev.undefinedapp

import android.app.Application
import com.ink1804.dev.common.network.domain.usecase.GptUseCase
import com.ink1804.dev.undefinedapp.di.KoinAppComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    //    @Inject
    val gptUseCase: GptUseCase by inject()

//    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .build()
//    }

    override fun onCreate() {
        super.onCreate()
        startDI()
//        appComponent.inject(this)
        Timber.plant(Timber.DebugTree())
        testGptRequest()
    }

    private fun startDI() {
        startKoin() {
            androidLogger()
            androidContext(this@App)
            modules(KoinAppComponent)
        }
    }

    private fun testGptRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            gptUseCase.completions()
        }
    }
}