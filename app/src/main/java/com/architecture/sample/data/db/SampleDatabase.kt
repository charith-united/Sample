package com.architecture.sample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.architecture.sample.data.db.dao.AccountDataDao
import com.architecture.sample.data.db.entity.AccountDataCacheEntity

@Database(entities = [AccountDataCacheEntity::class], version = 1)
abstract class SampleDatabase: RoomDatabase() {

    abstract fun accountDataDao(): AccountDataDao

    companion object {
        const val DATABASE_NAME = "sample_db"
    }
}