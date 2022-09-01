package com.said.myfavoriteanimals.data.api

import com.said.myfavoriteanimals.data.model.AnimalModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("api/breeds/image/random")
    suspend fun getAnimal(): Response<AnimalModel>

}