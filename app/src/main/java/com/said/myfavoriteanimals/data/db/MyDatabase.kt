package com.said.myfavoriteanimals.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.said.myfavoriteanimals.data.db.dao.AnimalDao
import com.said.myfavoriteanimals.data.db.entity.Animal

@Database(entities = [Animal::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
}