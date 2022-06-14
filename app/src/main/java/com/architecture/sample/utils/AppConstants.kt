package com.architecture.sample.utils

object AppConstants {
    const val EMAIL_REGEX_VALUE = "[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]+"
    const val PASSWORD_REGEX_VALUE = "(.*)"

    enum class Error(val message: String) {
        LOGIN_REQUEST_FAILED("Failed to make login request"),
        LOGIN_INVALID_CREDENTIALS("Login request failed with invalid credentials"),
    }

    enum class AccountResponseState(val code: Int, val message: String){
        LOGIN_INVALID_CREDENTIALS(1,"invalid username or password"),
        REGISTRATION_ALREADY_REGISTERED(1,"The email address you have entered is already registered"),
        REQUEST_SUCCESSFUL(0,"success"),
    }
}