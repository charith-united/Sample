package com.architecture.sample.data.network.request

data class AccountRegistrationRequest(
    val email: String,
    val name: String,
    val password: String
)