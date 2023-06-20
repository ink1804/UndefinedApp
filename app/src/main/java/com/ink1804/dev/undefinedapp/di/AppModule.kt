package com.ink1804.dev.undefinedapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.koin.dsl.module

val KoinAppModule = module {

}

@Module
class AppModule(
    @get:Provides val app: Application,
    @get:Provides val appContext: Context = app.applicationContext,
//    @get:Provides val lifecycleOwner: LifecycleOwner,
//    @get:Provides val contentResolver: ContentResolver = appContext.contentResolver
)