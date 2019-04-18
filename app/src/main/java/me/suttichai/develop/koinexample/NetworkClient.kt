package me.suttichai.develop.koinexample

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class NetworkClient {

    fun client(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor(logger)
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)

        builder.addInterceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url()
            val url = originalUrl.newBuilder().build()
            val request = originalRequest.newBuilder()
                .url(url)
                .build()

            chain.proceed(request)
        }

        return builder.build()
    }

    private val logger = HttpLoggingInterceptor.Logger { message ->
        Log.d("LOG API", message)
    }
}