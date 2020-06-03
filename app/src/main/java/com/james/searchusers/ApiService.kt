package com.james.searchusers

import com.james.searchusers.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/search/users")
    fun getUser(
        @Query("q") q: String?,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Call<Users>

}