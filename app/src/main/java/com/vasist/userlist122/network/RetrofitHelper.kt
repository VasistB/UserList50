package com.vasist.userlist122.network

import com.vasist.userlist122.JsonData.JsonData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val  url ="https://randomuser.me/api/"

object RetrofitHelper {
    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    interface QuoteService {
        @GET("?results=50")
        suspend fun getUserList(): Response<JsonData>

    }
}