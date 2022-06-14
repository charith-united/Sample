package com.architecture.sample.data.network.client

import com.architecture.network.BaseRetrofitClient

class SampleRetrofitClient(
    uri: String,
    logTag: String = "SampleRetrofitClient"
): BaseRetrofitClient(uri,logTag)