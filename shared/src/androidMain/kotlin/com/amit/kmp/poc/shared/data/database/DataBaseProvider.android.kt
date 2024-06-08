package com.amit.kmp.poc.shared.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.amit.kmp.poc.shared.KmpPocDb

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal actual class DataBaseDriver(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(KmpPocDb.Schema, context, DB_NAME)
    }
}
