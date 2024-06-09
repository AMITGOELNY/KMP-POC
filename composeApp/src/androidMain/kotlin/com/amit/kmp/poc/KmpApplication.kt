package com.amit.kmp.poc

import android.app.Application
import android.content.Context
import com.amit.kmp.poc.shared.di.initKoin
import org.koin.dsl.module

class KmpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            appModule = { module { single<Context> { this@KmpApplication } } },
        )
    }
}
