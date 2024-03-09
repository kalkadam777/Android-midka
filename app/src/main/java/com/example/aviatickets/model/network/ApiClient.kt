package com.example.aviatickets.model.network

import com.example.aviatickets.model.entity.Offer
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {

    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private const val BASE_URL = "https://my-json-server.typicode.com/estharossa/fake-api-demo/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    fun createService(): ApiService = retrofit.create(ApiService::class.java)

    interface ApiService {
        @GET("offer_list")
        suspend fun getOffers(): List<Offer>
    }
    /**
     * think about performing network request
     */
}