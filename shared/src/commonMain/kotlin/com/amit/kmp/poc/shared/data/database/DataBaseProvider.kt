package com.amit.kmp.poc.shared.data.database

import app.cash.sqldelight.db.SqlDriver
import com.amit.kmp.poc.shared.KmpPocDb
import org.koin.core.annotation.Single

internal const val DB_NAME = "Kmp-Poc-Db"

@Single([DatabaseProvider::class])
class DatabaseProvider(sqlDriver: SqlDriver) {
    val database: KmpPocDb = KmpPocDb(driver = sqlDriver)
}

internal expect class DataBaseDriver {
    fun createDriver(): SqlDriver
}
