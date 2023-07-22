package com.ink1804.dev.undefinedapp.di

import com.ink1804.dev.feature.main.di.KoinFeatureMainModule
import com.ink1804.dev.feature.map.di.KoinFeatureMapModule
import com.ink1804.dev.feature.profile.di.KoinFeatureProfileModule
import com.ink1804.dev.feature.splash.di.KoinFeatureSplashModule
import com.ink1804.dev.shared.common.network.di.KoinSharedTestApiModule
import org.koin.dsl.module


val KoinAppComponent = module {
    includes(
        //kmm
//        KoinConfigModule,
        KoinSharedTestApiModule,
//        KoinAppApiModule,
//        KoinGptApiModule,


        KoinNavigationModule,

        //features
        KoinFeatureSplashModule,
        KoinFeatureMainModule,
        KoinFeatureMapModule,
        KoinFeatureProfileModule,
    )
}
