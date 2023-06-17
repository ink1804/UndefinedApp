package com.ink1804.dev.undefinedapp

import android.app.Application
import com.ink1804.dev.common.network.domain.usecase.GptUseCase
import com.ink1804.dev.undefinedapp.di.AppComponent
import com.ink1804.dev.undefinedapp.di.AppModule
import com.ink1804.dev.undefinedapp.di.DaggerAppComponent
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class App : Application() {

    @Inject
    lateinit var gptUseCase: GptUseCase

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        Timber.plant(Timber.DebugTree())
        testGptRequest()
    }

    private fun testGptRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            gptUseCase.completions()
        }
    }
}