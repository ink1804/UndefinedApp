package com.ink1804.dev.undefinedapp.di

import com.ink1804.dev.common.network.data.di.AppApiModule
import com.ink1804.dev.common.network.data.di.CommonNetworkModule
import com.ink1804.dev.common.network.data.di.GptApiModule
import com.ink1804.dev.common.network.data.di.KoinAppApiModule
import com.ink1804.dev.common.network.data.di.KoinCommonNetworkModule
import com.ink1804.dev.common.network.data.di.KoinGptApiModule
import com.ink1804.dev.undefinedapp.App
import dagger.Component
import javax.inject.Singleton
import org.koin.dsl.module


val KoinAppComponent = module {
    includes(
        KoinConfigModule,
        KoinAppApiModule,
        KoinGptApiModule,
        KoinCommonNetworkModule,
    )
}

@Singleton
@Component(
    modules = [
        AppModule::class,
        ConfigsModule::class,
        CommonNetworkModule::class,
        GptApiModule::class,
        AppApiModule::class
    ]
)
interface AppComponent {
    fun inject(app: App)
}