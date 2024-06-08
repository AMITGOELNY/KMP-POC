package com.amit.kmp.poc.shared.di

import com.amit.kmp.poc.shared.data.database.DataBaseDriver
import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    single { DataBaseDriver().createDriver() }
    single { Darwin.create { } }

//    single<ObservableSettings>(named(ENCRYPTED_SETTINGS_NAME)) {
//        KeychainSettings(service = ENCRYPTED_SETTINGS_NAME).asObservableSettings()
//    }
//
//    single<ObservableSettings>(named(DEFAULT_SETTINGS_NAME)) {
//        NSUserDefaultsSettings(NSUserDefaults(suiteName = DEFAULT_SETTINGS_NAME))
//    }
}
