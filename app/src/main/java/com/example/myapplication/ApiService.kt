package com.example.myapplication

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @POST(value = "calis")
    fun validateCode(@Query(value="codigo")code:String):
            Call<Void>


    companion object Factory{
        private const val BASE_URL = "http://192.168.10.4/api/"
        fun create() : ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }

    }
}