package me.suttichai.develop.koinexample

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService constructor(private val config: NetworkConfig, private val client: NetworkClient) {

    fun create(): Service {
        return retrofit(config.ENDPOINT).create(Service::class.java)
    }

    private fun retrofit(endpoint: String): Retrofit {
        val builder = Retrofit.Builder()
            .client(client.client())
            .baseUrl(endpoint)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }
}