package com.architecture.sample.data.repo

import com.architecture.sample.data.db.SampleDatabase
import com.architecture.sample.data.db.mapper.AccountDataCacheMapper
import com.architecture.sample.data.model.AccountData
import com.architecture.sample.data.network.api.IAccountApi
import com.architecture.sample.data.network.entity.AccountDataNetworkEntity
import com.architecture.sample.data.network.mapper.AccountDataNetworkMapper
import com.architecture.sample.data.network.request.AccountLoginRequest
import com.architecture.sample.data.network.response.AccountLoginResponse
import com.architecture.sample.data.state.DataState
import com.architecture.sample.utils.AppConstants
import com.architecture.sample.utils.SampleException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AccountRepository @Inject constructor(
    @Inject val accountApi: IAccountApi,
    @Inject val networkMapper: AccountDataNetworkMapper,
    @Inject val database: SampleDatabase,
    @Inject val cacheMapper: AccountDataCacheMapper,
) {

    suspend fun authenticateAccount(
        email: String,
        password: String
    ): Flow<DataState<AccountData>> = flow {
        emit(DataState.Loading)

        try {
            val response = accountApi.authenticateAccount(AccountLoginRequest(email, password))
            when (response.message) {
                "success" -> {
                    response.entity?.let { networkEntity ->
                        // Convert to AccountData Model
                        val dataModel = networkMapper.mapFromEntity(networkEntity)

                        // Convert to AccountDataCacheEntity
                        val cacheEntity = cacheMapper.mapToEntity(dataModel)

                        // Persist profile
                        database.accountDataDao().insert(cacheEntity).let {
                            when(it) {
                                -1L -> emit(DataState.Failure(CancellationException("Failed to cache account")))
                                else -> emit(DataState.Success(dataModel))
                            }
                        }
                    } ?: kotlin.run {
                        emit(DataState.Failure(CancellationException("Invalid response for the success status code")))
                    }

                }
                else -> emit(DataState.Failure(CancellationException(response.message)))
            }
        } catch (e: Exception) {
            emit(DataState.Failure(e))
        }
    }

}