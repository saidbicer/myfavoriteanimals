package com.said.myfavoriteanimals.data.repository

import com.said.myfavoriteanimals.data.api.RetrofitAPI
import com.said.myfavoriteanimals.data.model.AnimalModel
import com.said.myfavoriteanimals.util.Resource
import java.lang.Exception
import javax.inject.Inject

class AnimalRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) : AnimalRepositoryInterface {

    override suspend fun getImageFromAPI(): Resource<AnimalModel> {
        return try {
            val response = retrofitAPI.getAnimal()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error("No data!", null)
        }
    }

}