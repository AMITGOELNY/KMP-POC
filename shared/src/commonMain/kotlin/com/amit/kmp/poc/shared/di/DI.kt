package com.amit.kmp.poc.shared.di

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module

const val DEFAULT_SETTINGS_NAME = "DEFAULT_SETTINGS"
const val ENCRYPTED_SETTINGS_NAME = "ENCRYPTED_SETTINGS"

fun initKoin(appModule: () -> Module): KoinApplication =
    startKoin {
        modules(
            appModule(),
            module { single { HttpClient(engine = get()) } },
            platformModule,
            RepositoryModule().module,
            UseCaseModule().module,
            DatabaseModule().module,
            SourcesModule().module,
            APIModule().module,
            storageModule,
            sharedViewModelModule,
        ).also {
            Logger.d("KMM Koin init Complete")
        }
    }

@org.koin.core.annotation.Module
@ComponentScan("com.amit.kmp.poc.shared.data.repository")
class RepositoryModule

@org.koin.core.annotation.Module
@ComponentScan("com.amit.kmp.poc.shared.domain.usecase")
class UseCaseModule

@org.koin.core.annotation.Module
@ComponentScan("com.amit.kmp.poc.shared.data.database")
class DatabaseModule

@org.koin.core.annotation.Module
@ComponentScan("com.amit.kmp.poc.shared.data.sources")
class SourcesModule

@org.koin.core.annotation.Module
@ComponentScan("com.amit.kmp.poc.shared.data.api")
class APIModule

internal expect val platformModule: Module

val sharedViewModelModule =
    module {
//    factory { SessionListViewModel(useCase = get()) }
    }

internal val storageModule =
    module {
//    single<PreferenceManager> {
//        PrefsManager(get(named(ENCRYPTED_SETTINGS_NAME)), get(named(DEFAULT_SETTINGS_NAME)))
//    }
    }
