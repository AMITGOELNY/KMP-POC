package com.amit.kmp.poc.shared.di

import com.amit.kmp.poc.shared.data.database.DataBaseDriver
import io.ktor.client.engine.okhttp.OkHttp
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

//    single<ObservableSettings>(named(ENCRYPTED_SETTINGS_NAME)) {
//        SharedPreferencesSettings(
//            delegate = EncryptedSharedPreferences.create(
//                get(),
//                ENCRYPTED_SETTINGS_NAME,
//                MasterKey.Builder(get())
//                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
//                    .build(),
//                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//            ),
//            commit = false
//        )
//    }
//
//    single<ObservableSettings>(named(DEFAULT_SETTINGS_NAME)) {
//        SharedPreferencesSettings(
//            delegate = get<Context>().getSharedPreferences(
//                DEFAULT_SETTINGS_NAME,
//                Context.MODE_PRIVATE
//            )
//        )
//    }
}