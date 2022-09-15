package com.said.myfavoriteanimals.data.repository

import android.content.Context
import android.widget.Toast
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.data.api.RetrofitAPI
import com.said.myfavoriteanimals.data.db.dao.AnimalDao
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.data.model.AnimalModel
import com.said.myfavoriteanimals.util.ConnectionChecker
import com.said.myfavoriteanimals.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception
import javax.inject.Inject

class AnimalRepository @Inject constructor(
    private val animalDao: AnimalDao,
    private val retrofitAPI: RetrofitAPI,
    private val connectionChecker: ConnectionChecker,
    private val context: Context
) : AnimalRepositoryInterface {

    override suspend fun insertAnimalToSQLite(animal: Animal) {
        animalDao.insertAnimal(animal)
    }

    override suspend fun getAnimalsFromSQLite(): List<Animal> {
        return animalDao.getAllAnimals()
    }

    override suspend fun getAnimalWithUrlFromSQLite(imgUrl: String): Animal? {
        return animalDao.getAnimalWithUrlFromSQLite(imgUrl)
    }

    override suspend fun getImageFromAPI(): Resource<AnimalModel> {
        if (!connectionChecker.check()) {
            return Resource.error("No connection", null)
        }

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
        } finally {
            Toast.makeText(context, context.getString(R.string.app_name), Toast.LENGTH_LONG).show()
        }
    }

}