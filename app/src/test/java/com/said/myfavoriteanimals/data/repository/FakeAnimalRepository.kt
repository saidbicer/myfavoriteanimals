package com.said.myfavoriteanimals.data.repository

import androidx.lifecycle.MutableLiveData
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.data.model.AnimalModel
import com.said.myfavoriteanimals.util.Resource

class FakeAnimalRepository : AnimalRepositoryInterface {

    private val animals = mutableListOf<Animal>()
    private val animalsLiveData = MutableLiveData<List<Animal>>(animals)

    override suspend fun insertAnimalToSQLite(animal: Animal) {
        if (getAnimalWithUrlFromSQLite(animal.imgUrl) == null) {
            animals.add(animal)
            refreshData()
        }
    }

    override suspend fun getAnimalsFromSQLite(): List<Animal> {
        return animals
    }

    override suspend fun getAnimalWithUrlFromSQLite(imgUrl: String): Animal? {
        return animals.filter { it.imgUrl == imgUrl }[0]
    }

    override suspend fun getImageFromAPI(): Resource<AnimalModel> {
        return Resource.success(
            AnimalModel(
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_4517.jpg",
                "success"
            )
        )
    }

    private fun refreshData() {
        animalsLiveData.postValue(animals)
    }
}