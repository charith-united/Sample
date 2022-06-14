package com.architecture.sample.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.architecture.sample.data.db.entity.AccountDataCacheEntity

@Dao
interface AccountDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AccountDataCacheEntity): Long

    @Query("SELECT * FROM accountData where ad_id = :id")
    suspend fun getAccountById(id: Int): AccountDataCacheEntity


    @Query("SELECT * FROM accountData where ad_email = :email")
    suspend fun getAccountByEmail(email: String): AccountDataCacheEntity

    // Select e1.firstName, e2.address from ent1 as e1, ent2 as 2 where e1.id = :id and e1.email = e2.email
}