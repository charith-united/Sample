package com.architecture.sample.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accountData")
data class AccountDataCacheEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "ad_id") val id: Int,
    @ColumnInfo(name = "ad_email") val email: String,
    @ColumnInfo(name = "ad_name") val name: String,
    @ColumnInfo(name = "ad_token") val token: String
)