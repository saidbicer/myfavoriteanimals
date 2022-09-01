package com.said.myfavoriteanimals.data.repository

import com.said.myfavoriteanimals.data.model.AnimalModel
import com.said.myfavoriteanimals.util.Resource

interface AnimalRepositoryInterface {
    suspend fun getImageFromAPI(): Resource<AnimalModel>
}