package com.amit.kmp.poc.shared.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.amit.kmp.poc.shared.KmpPocDb

internal actual class DataBaseDriver {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(KmpPocDb.Schema, DB_NAME)
    }
}
