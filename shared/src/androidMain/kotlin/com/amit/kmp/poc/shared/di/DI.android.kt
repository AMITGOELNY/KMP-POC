package com.amit.kmp.poc.shared.di

import com.amit.kmp.poc.shared.data.database.DataBaseDriver
import com.amit.kmp.poc.shared.presentation.feed.FeedViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    single { DataBaseDriver(get()).createDriver() }
    single {
        OkHttp.create {
//            preconfigured = okHttpClient
            config { retryOnConnectionFailure(true) }
        }
    }

    viewModel { FeedViewModel() }
}