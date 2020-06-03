package com.james.searchusers

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager private constructor() {
    private var okHttpClient = OkHttpClient()
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    companion object {
        private val manager = RetrofitManager()

        val client: Retrofit
            get() = manager.retrofit
    }
}
