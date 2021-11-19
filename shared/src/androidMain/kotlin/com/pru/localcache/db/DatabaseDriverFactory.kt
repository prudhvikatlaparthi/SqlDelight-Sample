package com.pru.localcache.db

import android.content.Context
import com.pru.localcache.LocalCacheDB
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = LocalCacheDB.Schema,
            context = context,
            name = "local_cache.db"
        )
    }

}
