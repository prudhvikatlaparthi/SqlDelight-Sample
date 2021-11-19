package com.pru.localcache.db

import com.pru.localcache.LocalCacheDB
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(LocalCacheDB.Schema, "test.db")
    }
}
