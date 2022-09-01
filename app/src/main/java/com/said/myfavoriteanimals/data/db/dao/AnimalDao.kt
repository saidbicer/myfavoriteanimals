package com.said.myfavoriteanimals.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.said.myfavoriteanimals.data.db.entity.Animal

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: Animal)

    @Query("SELECT * FROM animals")
    suspend fun getAllAnimals(): List<Animal>

    @Query("SELECT * FROM animals WHERE imgUrl = :imgUrl")
    suspend fun getAnimalFromUrl(imgUrl: String): Animal?
}