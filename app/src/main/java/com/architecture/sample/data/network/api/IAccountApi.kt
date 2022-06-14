package com.architecture.sample.data.network.api

import com.architecture.sample.data.network.request.AccountLoginRequest
import com.architecture.sample.data.network.request.AccountRegistrationRequest
import com.architecture.sample.data.network.response.AccountLoginResponse
import com.architecture.sample.data.network.response.AccountRegistrationResponse
import retrofit2.Call
import retrofit2.http.POST

interface IAccountApi {

    @POST("api/authaccount/login")
    fun authenticateAccount(request: AccountLoginRequest): AccountLoginResponse

    @POST("api/authaccount/register")
    fun registerAccount(request: AccountRegistrationRequest): AccountRegistrationResponse
}