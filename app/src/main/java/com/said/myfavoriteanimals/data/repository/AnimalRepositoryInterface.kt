package com.said.myfavoriteanimals.data.repository

import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.data.model.AnimalModel
import com.said.myfavoriteanimals.util.Resource

interface AnimalRepositoryInterface {
    suspend fun insertAnimalToSQLite(animal: Animal)
    suspend fun getAnimalsFromSQLite(): List<Animal>
    suspend fun getAnimalWithUrlFromSQLite(imgUrl : String): Animal?
    suspend fun getImageFromAPI(): Resource<AnimalModel>
}