package com.architecture.sample.data.network.response

import com.architecture.sample.data.network.entity.AccountDataNetworkEntity
import com.google.gson.annotations.SerializedName

data class AccountRegistrationResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val entity: AccountDataNetworkEntity? = null
)