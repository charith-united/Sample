package com.architecture.sample.data.network.entity

import com.google.gson.annotations.SerializedName

data class AccountDataNetworkEntity(
    @SerializedName("Email") val email: String,
    @SerializedName("Id") val id: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("Token") val token: String
)