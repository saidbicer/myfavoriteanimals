package com.said.myfavoriteanimals.data.repository

import androidx.lifecycle.MutableLiveData
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.data.model.AnimalModel
import com.said.myfavoriteanimals.util.Resource

class FakeAnimalRepository : AnimalRepositoryInterface {

    private val animals = mutableListOf<Animal>()
    private lateinit var animalModel: AnimalModel

    override suspend fun insertAnimalToSQLite(animal: Animal) {
        if (getAnimalWithUrlFromSQLite(animal.imgUrl) == null) {
            animals.add(animal)
        }
    }

    override suspend fun getAnimalsFromSQLite(): List<Animal> {
        return animals
    }

    override suspend fun getAnimalWithUrlFromSQLite(imgUrl: String): Animal? {
        val list = animals.filter { it.imgUrl == imgUrl }
        return if (list.isNotEmpty()) list[0] else null
    }

    override suspend fun getImageFromAPI(): Resource<AnimalModel> {
        return Resource.success(animalModel)
    }

    fun setAnimalModel(message: String, status: String) {
        animalModel = AnimalModel(message, status)
    }
}