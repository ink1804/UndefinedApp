package com.ink1804.dev.undefinedapp.di

import com.ink1804.dev.common.ui.ActivityContext
import org.koin.dsl.module


fun koinMainActivityModule(context: ActivityContext) = module {
    single { context }
}