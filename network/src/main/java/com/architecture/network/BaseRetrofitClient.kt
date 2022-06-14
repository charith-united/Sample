package com.architecture.network

import android.util.Log
import androidx.annotation.Keep
import com.google.gson.Gson
import com.united.network.utils.NetworkConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Keep
abstract class BaseRetrofitClient(
    private val baseUrl: String,
    private val tag: String = "BaseRetrofitClient",
    private val timeout: Long = NetworkConstants.DEFAULT_TIMEOUT,
    private val gson: Gson
) {
    /** ---------- Instance Variable(s) ---------------*/
    private val networkLogInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor { message ->
            Log.e(
                tag,
                message
            )
        }.apply { level = HttpLoggingInterceptor.Level.HEADERS }
    }

    private val appLogInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor { message ->
            Log.e(
                tag,
                message
            )
        }.apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .apply {
                connectTimeout(timeout, TimeUnit.SECONDS)
                writeTimeout(timeout, TimeUnit.SECONDS)
                readTimeout(timeout, TimeUnit.SECONDS)
                addNetworkInterceptor(networkLogInterceptor)
                addInterceptor(appLogInterceptor)
                getAdditionalInterceptors().forEach { addInterceptor(it) }
            }.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /** ---------- Protected Function(s) ---------------*/
    fun <T> create(service: Class<T>): T = retrofit.create(service)

    protected open fun getAdditionalInterceptors(): List<Interceptor> = listOf()
}