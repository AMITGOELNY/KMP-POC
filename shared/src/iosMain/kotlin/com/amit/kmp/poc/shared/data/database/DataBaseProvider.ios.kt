package com.amit.kmp.poc.shared.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.amit.kmp.poc.shared.KmpPocDb

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal actual class DataBaseDriver {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(KmpPocDb.Schema, DB_NAME)
    }
}
