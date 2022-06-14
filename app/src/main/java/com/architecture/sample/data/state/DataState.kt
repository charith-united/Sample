package com.architecture.sample.data.state

import java.lang.Exception

sealed class DataState<out X> {

    data class Success<out Y>(val data: Y): DataState<Y>()
    data class Failure(val exception: Exception): DataState<Nothing>()
    object Loading: DataState<Nothing>()
}
