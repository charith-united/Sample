package com.architecture.sample.data.di.module

import android.content.Context
import androidx.room.Room
import com.architecture.sample.BuildConfig
import com.architecture.sample.data.db.SampleDatabase
import com.architecture.sample.data.di.qualifier.AccountRetrofitClient
import com.architecture.sample.data.network.api.IAccountApi
import com.architecture.sample.data.network.client.SampleRetrofitClient
import com.architecture.sample.data.network.mapper.AccountDataNetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object AccountModule {

    @Provides
    @AccountRetrofitClient
    fun getAccountRetrofitClient(): SampleRetrofitClient =
        SampleRetrofitClient(BuildConfig.ACCOUNT_BASE_URI, "AccountRetrofitClient")

    @Provides
    fun getAccountApi(@AccountRetrofitClient retrofitClient: SampleRetrofitClient): IAccountApi =
        retrofitClient.create(IAccountApi::class.java)
}

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): SampleDatabase {
        return Room.databaseBuilder(
            context,
            SampleDatabase::class.java,
            SampleDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}